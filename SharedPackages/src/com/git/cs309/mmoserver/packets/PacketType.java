package com.git.cs309.mmoserver.packets;

public enum PacketType {

	SELF_PACKET((byte) 21), MOVE_PACKET((byte) 20), NEW_MAP_PACKET((byte) 19), ITEM_CONTAINER_PACKET(
			(byte) 18), PLAYER_EQUIPMENT_PACKET((byte) 17), CHARACTER_STATUS_PACKET((byte) 16), PLAYER_CHARACTER_PACKET(
					(byte) 15), EXTENSIVE_CHARACTER_PACKET((byte) 14), EXTENSIVE_OBJECT_PACKET(
							(byte) 12), ENTITY_UPDATE_PACKET((byte) 7), ENTITY_CLICK_PACKET(
									(byte) 5), INTERFACE_CLICK_PACKET((byte) 12), USER_STATUS_PACKET(
											(byte) 10), SERVER_MODULE_STATUS_PACKET((byte) 9), ADMIN_COMMAND_PACKET(
													(byte) 8), TEST_PACKET((byte) 6), EVENT_PACKET(
															(byte) 4), ERROR_PACKET((byte) 3), LOGIN_PACKET(
																	(byte) 2), MESSAGE_PACKET((byte) 1), NULL_PACKET(
																			(byte) 0);

	//Currently unused bytes: 21-255
	public static final byte NULL_PACKET_BYTE = 0;
	public static final byte MESSAGE_PACKET_BYTE = 1;
	public static final byte LOGIN_PACKET_BYTE = 2;
	public static final byte ERROR_PACKET_BYTE = 3;
	public static final byte EVENT_PACKET_BYTE = 4;
	public static final byte ENTITY_CLICK_PACKET_BYTE = 5;
	public static final byte TEST_PACKET_BYTE = 6;
	public static final byte ENTITY_UPDATE_PACKET_BYTE = 7;
	public static final byte ADMIN_COMMAND_PACKET_BYTE = 8;
	public static final byte SERVER_MODULE_STATUS_PACKET_BYTE = 9;
	public static final byte USER_STATUS_PACKET_BYTE = 10;
	public static final byte INTERFACE_CLICK_PACKET_BYTE = 12;
	public static final byte EXTENSIVE_OBJECT_PACKET_BYTE = 13;
	public static final byte EXTENSIVE_CHARACTER_PACKET_BYTE = 14;
	public static final byte PLAYER_CHARACTER_PACKET_BYTE = 15;
	public static final byte CHARACTER_STATUS_PACKET_BYTE = 16;
	public static final byte PLAYER_EQUIPMENT_PACKET_BYTE = 17;
	public static final byte ITEM_CONTAINER_PACKET_BYTE = 18;
	public static final byte NEW_MAP_PACKET_BYTE = 19;
	public static final byte MOVE_PACKET_BYTE = 20;
	public static final byte SELF_PACKET_BYTE = 11;
	//Packets needing implementation:
	/*
	 * ItemContainerPacket
	 */
	private final byte typeByte;

	private PacketType(final byte typeByte) {
		this.typeByte = typeByte;
	}

	public final byte getTypeByte() {
		return typeByte;
	}
}
