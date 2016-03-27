package com.git.cs309.serverscript.core;

import java.util.Stack;

import com.git.cs309.serverscript.ServerScriptEngine;

public final class ScriptEnvironment implements Runnable {
	private Stack<Frame> frameStack = new Stack<>();
	private final Script script;
	
	public ScriptEnvironment(final Script script) {
		this.script = script;
		Frame base = new Frame(ServerScriptEngine.getBottomFrame());
		frameStack.push(base);
	}
	
	public ScriptEnvironment(final Script script, final Frame base) {
		this.script = script;
		frameStack.push(base);
	}
	
	public Frame getCurrentFrame() {
		return frameStack.peek();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
