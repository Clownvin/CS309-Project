package com.git.cs309.server.map;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.git.cs309.server.util.ClassUtils;
import com.git.cs309.server.util.OnCreate;
import com.git.cs309.server.util.OnGarbageCollection;
import com.git.cs309.server.util.OnMove;

public abstract class EntityType<MapHandler extends AbstractMapHandler<MapHandler, ?, ?, ?, Key, ?, Type, Unit>, Key, NumberUnit extends Number, Type extends EntityType<MapHandler, Key, NumberUnit, Type, Unit>, Unit extends MapUnit<NumberUnit, Unit>>
		implements Clippable<NumberUnit, Type, Unit> {
	
	protected Unit position;
	protected MapHandler mapHandler;
	protected Key key;
	
	public EntityType(MapHandler mapHandler, Unit position, Key key) {
		this.mapHandler = mapHandler;
		this.key = key;
		this.position = position;
		ClassUtils.invokeMethodsAnnotatedWith(this, OnCreate.class);
	}
	
	public MapHandler getMapHandler() {
		return mapHandler;
	}

	public Unit getPosition() {
		return position;
	}

	public void setPosition(Unit position) {
		this.position = position;
		ClassUtils.invokeMethodsAnnotatedWith(this, OnMove.class);
	}

	public Key getKey() {
		return key;
	}
	
	@Override
	public final void finalize() {
		ClassUtils.invokeMethodsAnnotatedWith(this, OnGarbageCollection.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof EntityType)) {
			return false;
		}
		EntityType e = (EntityType) other;
		return e.getKey().equals(getKey()) && e.getPosition().compareTo(getPosition()) == 0;
	}
}
