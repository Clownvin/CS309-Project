package com.git.cs309.server.map;

/**
 * 
 * @author Clownvin
 *
 * @param <Key>
 * @param <NumberFormat>
 * @param <InstanceType>
 * @param <Type>
 * @param <Unit>
 */
public interface AbstractMapFactory<
	Definition extends AbstractMapDefinition<Key, NumberFormat, ?, Unit>,
	Key, NumberFormat extends Number,
	InstanceType extends AbstractInstanceMap<Definition, Key, NumberFormat, Type, Unit>,
	Type extends EntityType<?, Key, NumberFormat, Type, Unit>, 
	Unit extends MapUnit<NumberFormat, Unit>>
{
	public InstanceType createMap(Key key);
	
	public void loadMapDefinitions();
	
}
