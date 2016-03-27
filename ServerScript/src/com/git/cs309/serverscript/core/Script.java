package com.git.cs309.serverscript.core;

import java.util.List;

import com.git.cs309.serverscript.core.action.Action;

public final class Script {
	private final List<Action> actions;
	
	public Script(List<Action> actions) {
		this.actions = actions;
	}
	
	public List<Action> getActions() {
		return actions;
	}
}
