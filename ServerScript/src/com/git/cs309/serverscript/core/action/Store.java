package com.git.cs309.serverscript.core.action;

import com.git.cs309.serverscript.core.ScriptArguments;
import com.git.cs309.serverscript.core.ScriptEnvironment;
import com.git.cs309.serverscript.types.ScriptObject;

public class Store extends Action {
	private final String desination;
	private final String newValue;
	
	public Store(final String destination, final String newValue) {
		this.desination = destination;
		this.newValue = newValue;
	}

	@Override
	public void perform(ScriptEnvironment environment) {
		ScriptObject value = null;
		if (newValue.endsWith(")")) {
			String[] methodSplit = newValue.split(".");
			ScriptObject object = environment.getCurrentFrame().getObject(methodSplit[0]);
			for (int i = 1; i < methodSplit.length; i++) {
				String methodName = methodSplit[i].substring(0, methodSplit[i].indexOf('('));
				String pre = methodSplit[i].replace(methodName, "");
				ScriptArguments arguments = new ScriptArguments(pre, environment);
				try {
					if (i == methodSplit.length - 1) {
						value = new ScriptObject(object.invoke(methodName, arguments));
					} else {
						object = object.invoke(methodName, arguments);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			environment.getCurrentFrame().setObject(desination, environment.getCurrentFrame().getObject(newValue));
		}
		environment.getCurrentFrame().setObject(desination, value);
	}
}
