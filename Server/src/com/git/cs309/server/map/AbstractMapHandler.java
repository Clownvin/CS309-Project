package com.git.cs309.server.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Clownvin
 *
 * @param <Definition>
 * @param <InstanceType>
 * @param <Factory>
 * @param <Key>
 * @param <NumberFormat>
 * @param <Type>
 * @param <Unit>
 */

public abstract class AbstractMapHandler<
	MapHandler extends AbstractMapHandler<MapHandler, Definition, InstanceType, Factory, Key, NumberFormat, Type, Unit>,
	Definition extends AbstractMapDefinition<Key, NumberFormat, Type, Unit>,
	InstanceType extends AbstractInstanceMap<Definition, Key, NumberFormat, Type, Unit>,
	Factory extends AbstractMapFactory<Definition, Key, NumberFormat, InstanceType, Type, Unit>,
	Key,
	NumberFormat extends Number, Type extends EntityType<MapHandler, Key, NumberFormat, Type, Unit>,
	Unit extends MapUnit<NumberFormat, Unit>>
{
	protected final Factory mapFactory;
	protected final HashMap<Key, InstanceType> instanceMap = new HashMap<>();
	protected final List<InstanceType> instanceList = new ArrayList<>();

	public AbstractMapHandler(Factory mapFactory) {
		this.mapFactory = mapFactory;
	}

	public Factory getMapFactory() {
		return mapFactory;
	}

	public Key createMap(Key definitionKey) {
		InstanceType newMap = mapFactory.createMap(definitionKey);
		instanceMap.put(newMap.getKey(), newMap);
		instanceList.add(newMap);
		return newMap.getKey();
	}
	
	public InstanceType getMap(Key mapKey) {
		return instanceMap.get(mapKey);
	}
	
	public InstanceType getMap(Unit unit) {
		for (InstanceType instance : instanceList) {
			if (!instance.containsUnit(unit))
				continue;
			return instance;
		}
		throw new IllegalStateException("No map for given unit: "+unit);
	}
}
