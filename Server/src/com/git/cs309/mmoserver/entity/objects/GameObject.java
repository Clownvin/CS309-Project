package com.git.cs309.mmoserver.entity.objects;

import com.git.cs309.mmoserver.entity.Entity;
import com.git.cs309.mmoserver.entity.EntityClassification;
import com.git.cs309.mmoserver.map.MapHandler;
import com.git.cs309.mmoserver.map.Point;
import com.git.cs309.mmoserver.packets.ExtensiveObjectPacket;
import com.git.cs309.mmoserver.packets.Packet;
import com.git.cs309.mmoserver.util.ClosedIDSystem;

public class GameObject extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3289635025699381316L;
	private transient final ObjectDefinition definition;

	public GameObject(MapHandler mapHandler, ObjectDefinition definition, Point position) {
		super(mapHandler, position, ClosedIDSystem.getTag(), definition.getObjectID(), definition.getObjectName());
		this.definition = definition;
	}

	@Override
	public boolean canWalkThrough() {
		return definition.getWalkable();
	}

	public ObjectDefinition getDefinition() {
		return definition;
	}

	public boolean isServerOnly() {
		return definition.isServerOnly();
	}

	@Override
	public EntityClassification getEntityType() {
		return EntityClassification.OBJECT;
	}

	@Override
	public Packet getExtensivePacket() {
		return new ExtensiveObjectPacket(null, getUniqueID(), definition.getObjectID(), x, y,
				definition.getObjectName());
	}

}
