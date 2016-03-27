package com.git.cs309.mmoserver.map;

import java.util.HashSet;
import java.util.Set;

import com.git.cs309.mmoserver.Config;
import com.git.cs309.mmoserver.entity.Entity;
import com.git.cs309.server.map.AbstractMapHandler;

public final class MapHandler extends AbstractMapHandler<MapHandler, MapDefinition, Map, MapFactory, String, Integer, Entity, Point>{
	
	public MapHandler(MapFactory mapFactory) {
		super(mapFactory);
	}

	private final Set<Map> maps = new HashSet<>();

	public final Entity getEntityAtPosition(final int instanceNumber, final Point point) {
		Map map = getMapContainingPosition(point);
		if (map == null) {
			return null;
		}
		return map.getEntity(point);
	}

	public final Map getMapContainingPosition(final Point point) {
		for (Map map : maps) {
			if (map.getZ() == point.getZ() && map.containsUnit(point) && map.getInstanceNumber() == point.getInstance()) {
				return map;
			}
		}
		throw new RuntimeException("No map for position.");
	}

	public final Map getMapContainingEntity(final Entity entity) {
		for (Map map : maps) {
			if (map.getZ() == entity.getZ() && map.containsUnit(entity.getPosition())
					&& map.getInstanceNumber() == entity.getInstanceNumber()) {
				return map;
			}
		}
		throw new RuntimeException("Entity is not in a map.");
	}

	public final void loadMaps() {
		maps.clear();
		addMap(mapFactory.createMap("island", Config.GLOBAL_INSTANCE));
		for (Map map : maps) {
			map.loadSpawns();
		}
	}

	final void addMap(Map map) {
		if (maps.contains(map)) {
			return;
		}
		maps.add(map);
	}

	final void removeMap(Map map) {
		if (maps.contains(map))
			maps.remove(map);
	}
}
