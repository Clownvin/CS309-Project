package com.git.cs309.mmoserver.packets;

import com.git.cs309.mmoserver.connection.Connection;

public final class EntityClickHandler {
	public static final void handlePacket(EntityClickPacket packet) {
		System.out.println("Entity clicked");
		Connection connection = (Connection) packet.getConnection();
		System.out.println("Clicked X,Y: " + packet.getEntityX() + ", " + packet.getEntityY());
		connection.getUser().getCurrentCharacter().walkTo(packet.getEntityX(), packet.getEntityY());
	}
}
