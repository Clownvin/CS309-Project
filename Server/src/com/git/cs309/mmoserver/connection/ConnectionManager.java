package com.git.cs309.mmoserver.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.git.cs309.mmoserver.Config;
import com.git.cs309.mmoserver.Main;
import com.git.cs309.mmoserver.characters.user.Rights;
import com.git.cs309.mmoserver.packets.Packet;
import com.git.cs309.mmoserver.packets.PacketHandler;
import com.git.cs309.mmoserver.packets.PacketType;
import com.git.cs309.mmoserver.util.TickProcess;

/**
 * 
 * @author Group 21
 *
 *         ConnectionManager object, which extends TickProcess. Houses all
 *         connections, and, each tick, collects the single most recent packet
 *         from each connection And processes them with the help of
 *         PacketHandler. This is, probably, going to be the slowest of all the
 *         tick reliants, aside from NPCManager, which may turn out to be
 *         slower. Another added functionality of this class is that it has
 *         methods to obtain a connection based on connection IP. A neat feature
 *         about the design of this (due to TickProcess) is that whenever this
 *         fails, the connections do NOT get removed, and all the methods still
 *         work, so it can still take on new connections.
 */
public final class ConnectionManager extends TickProcess {
	private final Map<String, Connection> connectionMap = new HashMap<>(); // Could hold both username -> connection and ip -> connection. But will probably only hold ip -> connection, since that's all that's needed.
	private final List<Connection> connections = new ArrayList<>(Config.MAX_CONNECTIONS);
	private Object waitObject = new Object();

	public ConnectionManager() {
		super("ConnectionManager");
		ConnectionManager predecessor = Main.getConnectionManager();
		if (predecessor != null) {
			waitObject = predecessor.waitObject;
			connectionMap.putAll(predecessor.connectionMap);
			connections.addAll(predecessor.connections);
		}
		predecessor = null;
	}

	/**
	 * Adds a connection to the connection list, so it can be processed over and
	 * found.
	 * 
	 * @param connection
	 *            connection to add to list.
	 */
	public void addConnection(final Connection connection) {
		synchronized (connectionMap) {
			connectionMap.put(connection.getIP(), connection); // Add connection to "IP->Connection" map
		}
		synchronized (connections) {
			connections.add(connection); // Add connection to list.
			System.out.println("Connection joined: " + connection.getIP());
		}
	}

	@Override
	public void ensureSafeClose() {
		//Not required
	}

	/**
	 * Determines whether or not the ConnectionManager is full.
	 * 
	 * @return true if max capacity has been reached, false if not.
	 */
	public synchronized boolean full() {
		return connections.size() >= Config.MAX_CONNECTIONS;
	}

	/**
	 * Gets a connection from the connection map based on the IP provided.
	 * 
	 * @param ip
	 *            IP of the connection to get.
	 * @return the connection sharing the same IP, or null if one does not
	 *         exist.
	 */
	public synchronized Connection getConnectionForIP(final String ip) {
		return connectionMap.get(ip);
	}

	public Object getWaitObject() {
		return waitObject;
	}

	/**
	 * Checks whether or not an Connection with the IP provided
	 * 
	 * @param ip
	 *            IP to check if is already connected.
	 * @return true if the connection map contains IP as a key, false if not.
	 */
	public boolean ipConnected(String ip) {
		synchronized (connectionMap) {
			return connectionMap.containsKey(ip);
		}
	}

	/**
	 * Removes a connection from the list and map.
	 * 
	 * @param connection
	 *            connection to remove.
	 * @return the same connection.
	 */
	public Connection removeConnection(final Connection connection) {
		synchronized (connectionMap) {
			connectionMap.remove(connection.getIP());
		}
		synchronized (connections) {
			connections.remove(connection);
		}
		return connection;
	}

	/**
	 * Removes a connection from the list and map for the given IP
	 * 
	 * @param ip
	 *            IP of connection to remove
	 * @return the connection for the IP
	 */
	public Connection removeConnection(final String ip) {
		Connection connection = getConnectionForIP(ip);
		synchronized (connectionMap) {
			connectionMap.remove(connection);
		}
		synchronized (connections) {
			connections.remove(connection);
		}
		return connection;
	}

	/**
	 * Sends a packet to all connections in list.
	 * 
	 * @param packet
	 *            packet to send
	 */
	public void sendPacketToAllConnections(final Packet packet) {
		synchronized (connections) {
			for (Connection connection : connections) {
				if (connection.isLoggedIn())
					connection.addOutgoingPacket(packet);
			}
		}
	}

	public void sendPacketToConnectionsWithRights(final Packet packet, final Rights rights) {
		synchronized (connections) {
			for (Connection connection : connections) {
				if (connection.isLoggedIn() && (connection.getUser().getRights() == Rights.ADMIN
						|| (rights == Rights.MOD && connection.getUser().getRights() != Rights.PLAYER)))
					connection.addOutgoingPacket(packet);
			}
		}
	}

	@Override
	protected void tickTask() {
		final List<Packet> packets = new ArrayList<>(connections.size()); // New list for packets.
		synchronized (connections) {
			for (int i = 0; i < connections.size(); i++) {
				if (connections.get(i).isDisconnected()) {
					System.out.println("Connection disconnected: " + removeConnection(connections.get(i--)).getIP()); // Send message and remove.
					continue;
				}
				Packet packet = connections.get(i).getPacket();
				if (packet != null && packet.getPacketType() != PacketType.NULL_PACKET) {
					packets.add(packet);
				}
			}
		}
		synchronized (waitObject) {
			waitObject.notifyAll(); // Since all connections are waiting on this class's singleton, notifyAll wakes them up so they can start reading packets again.
		}
		for (Packet packet : packets) {
			PacketHandler.handlePacket(packet); // Handle all the packets.
		}
		packets.clear();
	}
}
