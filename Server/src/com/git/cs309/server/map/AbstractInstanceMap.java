package com.git.cs309.server.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.git.cs309.server.util.Keyed;

/**
 * 
 * @author Clownvin
 *
 * @param <Key>
 * @param <NumberFormat>
 * @param <Type>
 * @param <Unit>
 */
public abstract class AbstractInstanceMap<Definition extends AbstractMapDefinition<Key, NumberFormat, ?, Unit>, Key, NumberFormat extends Number, Type extends EntityType<?, Key, NumberFormat, Type, Unit>, Unit extends MapUnit<NumberFormat, Unit>> implements Keyed<Key> {
	protected volatile HashMap<Key, Type> entities = new HashMap<>();
	protected volatile Set<Type> entitySet = new HashSet<>();

	public void add(Type entity) {
		if (entity == null) {
			throw new IllegalStateException("Entity cannot be equal to null.");
		}
		entities.put(entity.getKey(), entity);
		entitySet.add(entity);
	}

	public Type getForKey(Key key) {
		return entities.get(key);
	}

	public void move(Type entity, Unit position) throws CollisionException {
		for (Type e : entitySet) {
			if (e.getCollisionRadius().doubleValue() + entity.getCollisionRadius().doubleValue() < e.getPosition()
					.distanceBetween(position).doubleValue())
				continue;
			if (e.checkCollision(entity, position))
				throw new CollisionException(e);
		}
		entity.setPosition(position);
	}
	
	public void remove(Type entity) {
		
	}
	
	public abstract boolean containsUnit(Unit unit);
}
