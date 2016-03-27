package com.git.cs309.serverscript.core;

import java.util.HashMap;

import com.git.cs309.serverscript.types.ScriptObject;

public final class Frame {
	private final Frame upperFrame;
	private volatile HashMap<String, ScriptObject> objects = new HashMap<>();
	
	public Frame(Frame upperFrame) {
		this.upperFrame = upperFrame;
	}
	
	public Frame getUpperFrame() {
		return upperFrame;
	}
	
	public ScriptObject getObject(String identifier) {
		Frame currentFrame = this;
		do {
			if (currentFrame.objects.containsKey(identifier)) {
				return currentFrame.objects.get(identifier);
			}
		} while (currentFrame != null);
		throw new RuntimeException("No object for identifier.");
	}
	
	public void setObject(String identifier, ScriptObject object) {
		objects.put(identifier, object);
	}
}
