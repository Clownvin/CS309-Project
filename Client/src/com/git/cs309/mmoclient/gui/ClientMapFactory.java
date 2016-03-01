package com.git.cs309.mmoclient.gui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class ClientMapFactory {
	private static final ClientMapFactory INSTANCE = new ClientMapFactory(Config.MAP_DEFINITIONS_FOLDER);

	public static final ClientMapFactory getInstance() {
		return INSTANCE;
	}

	private final HashMap<String, ClientMapDefinition> mapDefinitions = new HashMap<>();
	private final String mapFolder;

	private ClientMapFactory(final String mapFolder) {
		this.mapFolder = mapFolder;
		loadDefinitions();
	}

	public synchronized final ClientMap createMap(final String mapName, final int instanceNumber) {
		ClientMapDefinition definition = ClientMapDefinition.get(mapName.toLowerCase());
		if (definition == null) {
			throw new RuntimeException("No map definition for map name: " + mapName);
		}
		return new ClientMap(definition, instanceNumber);
	}

	public synchronized void loadDefinitions() {
		ClientMapDefinition.clear();
		File folder = new File(mapFolder);
		for (File mapFile : folder.listFiles()) {
			if (!mapFile.getName().endsWith(".map")) {
				continue;
			}
			try {
				ClientMapDefinition definition = ClientMapParser.parseMapDefinitionFromFile(mapFile);
				if (definition == null) {
					continue;
				}
				ClientMapDefinition.put(definition.getMapName().toLowerCase(), definition);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
}
