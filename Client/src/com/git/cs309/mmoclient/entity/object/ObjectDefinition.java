package com.git.cs309.mmoclient.entity.object;

public final class ObjectDefinition {
	private final int objectID;
	private final String objectName;
	private final boolean walkable;

	public ObjectDefinition(final String objectName, final int objectID, final boolean walkable) {
		this.objectID = objectID;
		this.objectName = objectName;
		this.walkable = walkable;
	}

	public final int getObjectID() {
		return objectID;
	}

	public final String getObjectName() {
		return objectName;
	}

	public boolean getWalkable() {
		return walkable;
	}
}
