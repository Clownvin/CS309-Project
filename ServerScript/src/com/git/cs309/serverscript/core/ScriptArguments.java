package com.git.cs309.serverscript.core;

public class ScriptArguments {
	private final String[] arguments;
	private final ScriptEnvironment environment;
	
	public ScriptArguments(final String pre, final ScriptEnvironment environment) {
		this.environment = environment;
		this.arguments = pre.substring(pre.indexOf("(") + 1, pre.lastIndexOf(")")).split(",");
	}
	
	public Object[] getArguments() {
		Object[] argumentObjects = new Object[arguments.length];
		int index = 0;
		for (String argument : arguments) {
			argumentObjects[index++] = environment.getCurrentFrame().getObject(argument.trim());
		}
		return argumentObjects;
	}
}
