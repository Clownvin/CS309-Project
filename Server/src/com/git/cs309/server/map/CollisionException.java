package com.git.cs309.server.map;

public class CollisionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2663077828829724018L;

	private final EntityType<?, ?, ?, ?, ?> type;

	public CollisionException(EntityType<?, ?, ?, ?, ?> m) {
		this.type = m;
	}

	public EntityType<?, ?, ?, ?, ?> getCollidedWith() {
		return type;
	}
}
