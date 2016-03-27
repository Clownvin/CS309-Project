package com.git.cs309.mmoserver.map;

import com.git.cs309.mmoserver.util.MathUtils;
import com.git.cs309.server.map.MapUnit;

public final class Point implements MapUnit<Integer, Point> {
	private int x, y, z, instance;
	
	public Point(int x, int y, int z, int instance) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getInstance() {
		return instance;
	}
	
	@Override
	public Integer distanceBetween(Point otherUnit) {
		if (instance != otherUnit.instance) {
			return Integer.MAX_VALUE;
		}
		return (int) MathUtils.distance(x, y, otherUnit.getX(), otherUnit.getY());
	}

	@Override
	public int compareTo(Point o) {
		int yResult = o.getY() - y;
		if (yResult != 0)
			return yResult;
		return o.getX() - x;
	}

}
