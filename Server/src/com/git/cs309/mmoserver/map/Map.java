package com.git.cs309.mmoserver.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.git.cs309.mmoserver.Config;
import com.git.cs309.mmoserver.connection.Connection;
import com.git.cs309.mmoserver.entity.Entity;
import com.git.cs309.mmoserver.entity.EntityClassification;
import com.git.cs309.mmoserver.entity.characters.user.PlayerCharacter;
import com.git.cs309.mmoserver.entity.characters.user.User;
import com.git.cs309.mmoserver.entity.characters.user.UserManager;
import com.git.cs309.mmoserver.entity.objects.GameObject;
import com.git.cs309.mmoserver.entity.objects.GameObjectFactory;
import com.git.cs309.mmoserver.items.GroundItemStack;
import com.git.cs309.mmoserver.items.ItemStack;
import com.git.cs309.mmoserver.packets.EntityUpdatePacket;
import com.git.cs309.mmoserver.packets.NewMapPacket;
import com.git.cs309.mmoserver.packets.Packet;
import com.git.cs309.mmoserver.util.MathUtils;
import com.git.cs309.server.map.AbstractInstanceMap;
import com.git.cs309.mmoserver.entity.characters.npc.NPCFactory;

public final class Map extends AbstractInstanceMap<MapDefinition, String, Integer, Entity, Point> {
	
	private final int instanceNumber;
	private final MapDefinition definition;
	private volatile GroundItemStack[][] groundItems;
	private volatile Set<Entity> entitySet = new HashSet<>();
	private volatile Set<PlayerCharacter> playerSet = new HashSet<>();
	private final int[][] pathingMap;

	public Map(final MapDefinition definition, final int instanceNumber) {
		this.instanceNumber = instanceNumber;
		this.definition = definition;
		pathingMap = new int[definition.getWidth()][definition.getHeight()];
		groundItems = new GroundItemStack[definition.getWidth()][definition.getHeight()];
		setMapToNulls();
		setItemsToNulls();
	}

	public int[][] getPathingMap() {
		int[][] copy = new int[pathingMap.length][pathingMap[0].length];
		for (int i = 0; i < pathingMap.length; i++) {
			System.arraycopy(pathingMap[i], 0, copy[i], 0, pathingMap[i].length);
		}
		return copy;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Map)) {
			return false;
		}
		Map otherMap = (Map) other;
		return otherMap.instanceNumber == instanceNumber && otherMap.definition.equals(definition);
	}

	public Entity[] getEntities(final Point point) {
		assert (containsUnit(point));
		List<Entity> entities = new ArrayList<>();
		for (Entity entity : entitySet) {
			if (entity.getX() == point.getX() && entity.getY() == point.getY()) {
				entities.add(entity);
			}
		}
		return entities.toArray(new Entity[entities.size()]);
	}

	public boolean walkable(final Point point) {
		assert (containsUnit(point));
		return pathingMap[globalToLocalX(point.getX())][globalToLocalY(point.getY())] == PathFinder.EMPTY;
	}

	public Entity getEntity(final int uniqueId, final Point point) {
		assert (containsUnit(point));
		for (Entity entity : entitySet) {
			if (entity.getX() == point.getX() && entity.getY() == point.getY() && entity.getUniqueID() == uniqueId) {
				return entity;
			}
		}
		throw new RuntimeException("No entity at position (" + point.getX() + ", " + point.getY() + ") with ID: " + uniqueId);
	}

	public Entity getEntity(final Point point) {
		assert (containsUnit(point));
		for (Entity entity : entitySet) {
			if (entity.getX() == point.getX() && entity.getY() == point.getY()) {
				return entity;
			}
		}
		throw new RuntimeException("No entity at position (" + point.getX() + ", " + point.getY() + ")");
	}

	public int getHeight() {
		return definition.getHeight();
	}

	public final int getInstanceNumber() {
		return instanceNumber;
	}

	public int getWidth() {
		return definition.getWidth();
	}

	public final int globalToLocalX(int x) {
		return x - getXOrigin();
	}

	public final int globalToLocalY(int y) {
		return y - getYOrigin();
	}

	public final int localToGlobalX(int x) {
		return x + getXOrigin();
	}

	public final int localToGlobalY(int y) {
		return y + getYOrigin();
	}

	public final int getXOrigin() {
		return definition.getXOrigin();
	}

	public final int getYOrigin() {
		return definition.getYOrigin();
	}

	public int getZ() {
		return definition.getZ();
	}

	public void sendPacketToPlayers(Packet packet) {
		for (PlayerCharacter e : playerSet) {
			User user = UserManager.getUserForUserID(e.getUniqueID());
			if (user == null) {
				continue;
			}
			Connection userConnection = (Connection) user.getConnection();
			if (userConnection == null) {
				continue;
			}
			userConnection.addOutgoingPacket(packet);
		}
	}

	private void setMapToNulls() {
		for (int i = 0; i < pathingMap.length; i++) {
			for (int j = 0; j < pathingMap[i].length; j++) {
				pathingMap[i][j] = -1;
			}
		}
	}

	private void setItemsToNulls() {
		for (int i = 0; i < groundItems.length; i++) {
			for (int j = 0; j < groundItems[i].length; j++) {
				groundItems[i][j] = null;
			}
		}
	}

	void loadSpawns() {
		for (Spawn spawn : definition.getSpawns()) {
			switch (spawn.getType()) {
			case Spawn.CHARACTER:
				NPCFactory.getInstance().createNPC(spawn.getName(), spawn.getX(), spawn.getY(), definition.getZ(),
						instanceNumber);
				break;
			case Spawn.OBJECT:
				GameObjectFactory.getInstance().createGameObject(spawn.getName(), new Point(spawn.getX(), spawn.getY(), definition.getZ(), instanceNumber));
				break;
			case Spawn.NULL:
				break;
			default:
				System.err.println("No case for spawn type when loading map spawns: " + spawn.getType());
				break;
			}
		}
	}

	@Override
	public String getKey() {
		return "map."+definition.getMapName();
	}

	@Override
	public boolean containsUnit(Point unit) {
		return getXOrigin() + getWidth() > unit.getX() && unit.getX() >= getXOrigin() && getYOrigin() + getHeight() > unit.getY()
				&& unit.getY() >= getYOrigin();
	}
}
