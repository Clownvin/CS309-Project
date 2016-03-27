package com.git.cs309.server.map;

public interface MapUnit<NumberFormat extends Number, Unit extends MapUnit<NumberFormat, Unit>> extends Comparable<Unit> {

	public NumberFormat distanceBetween(Unit otherUnit);
}
