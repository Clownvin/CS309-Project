package com.git.cs309.mmoserver.map;

public final class Spawn {
	public static final byte OBJECT = 1;
	public static final byte NULL = 2; // Shouldn't be sent
	private final byte type;
	private final String name;
	private final int x;
	private final int y;
	
	public Spawn(final byte type, final String name, final int x, final int y) {
		this.type = type;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public byte getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
