package com.git.cs309.server.map;

public interface Clippable<NumberFormat extends Number, Type extends Clippable<NumberFormat, Type, Unit>, Unit extends MapUnit<NumberFormat, Unit>> {

	/**
	 * Checks if there has been a collision between two Clippables of the same
	 * type.
	 * 
	 * @param other
	 *            other Clippable
	 * @return true if a collision has occurred, false if it hasn't.
	 */
	public boolean checkCollision(Type other);

	public boolean checkCollision(Type other, Unit othersUnits);

	public boolean withinCollisionRadius(Type other);

	public boolean withinCollisionRadius(Unit position);

	public NumberFormat getCollisionRadius();
}
