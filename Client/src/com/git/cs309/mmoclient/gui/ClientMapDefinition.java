package com.git.cs309.mmoclient.gui;

import java.io.Serializable;
import java.util.Collection;


public final class ClientMapDefinition implements Serializable{
	private static final long serialVersionUID = 3174752973855574603L;
	private final String mapName;
	private final int width;
	private final int height;
	private final int xOrigin;
	private final int yOrigin;
	private final int z;
	private final Collection<Spawn> spawns;

	public ClientMapDefinition(final String mapName, final int xOrigin, final int yOrigin, final int z, final int width,
			final int height, final Collection<Spawn> spawns) {
		this.mapName = mapName;
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.z = z;
		this.width = width;
		this.height = height;
		this.spawns = spawns;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClientMapDefinition)) {
			return false;
		}
		ClientMapDefinition Clientmap = (ClientMapDefinition) other;
		return Clientmap.xOrigin == xOrigin && Clientmap.yOrigin == yOrigin && Clientmap.z == z && Clientmap.width == width
				&& Clientmap.height == height;
	}

	public int getHeight() {
		return height;
	}

	public final String getMapName() {
		return mapName;
	}

	public Collection<Spawn> getSpawns() {
		return spawns;
	}

	public final int getWidth() {
		return width;
	}

	public int getxOrigin() {
		return xOrigin;
	}

	public int getyOrigin() {
		return yOrigin;
	}

	public int getZ() {
		return z;
	}

}
