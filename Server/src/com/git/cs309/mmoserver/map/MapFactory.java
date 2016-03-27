package com.git.cs309.mmoserver.map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.git.cs309.mmoserver.entity.Entity;
import com.git.cs309.server.map.AbstractMapFactory;

public final class MapFactory implements AbstractMapFactory<MapDefinition, String, Integer, Map, Entity, Point> {

	private final HashMap<String, MapDefinition> mapDefinitions = new HashMap<>();
	private final String mapFolder;

	public MapFactory(final String mapFolder) {
		this.mapFolder = mapFolder;
		loadMapDefinitions();
	}

	public synchronized final Map createMap(final String mapName, final int instanceNumber) {
		MapDefinition definition = mapDefinitions.get(mapName.toLowerCase());
		if (definition == null) {
			throw new RuntimeException("No map definition for map name: " + mapName);
		}
		return new Map(definition, instanceNumber);
	}
	
	@Override
	public Map createMap(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadMapDefinitions() {
		mapDefinitions.clear();
		File folder = new File(mapFolder);
		for (File mapFile : folder.listFiles()) {
			if (!mapFile.getName().endsWith(".map")) {
				continue;
			}
			try {
				MapDefinition definition = MapParser.parseMapDefinitionFromFile(mapFile);
				if (definition == null) {
					continue;
				}
				mapDefinitions.put(definition.getMapName().toLowerCase(), definition);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
}
