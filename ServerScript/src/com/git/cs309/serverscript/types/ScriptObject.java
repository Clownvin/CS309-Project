package com.git.cs309.serverscript.types;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

public final class ScriptObject {
	private Object containedObject;
	
	private final HashMap<String, Method> methods = new HashMap<>();
	
	public ScriptObject(Object toContain) {
		set(toContain);
	}
	
	public void set(Object containedObject) {
		methods.clear();
		this.containedObject = containedObject;
		for (Method method : this.getClass().getMethods()) {
			methods.put(method.getName(), method);
		}
	}
	
	public Object getContainedObject() {
		return containedObject;
	}
	
	public Class<?> getType() {
		return containedObject.getClass();
	}
	
	@Override
	public String toString() {
		return containedObject.toString();
	}
	
	public Method getMethod(String method) {
		return methods.get(method);
	}
	
	public void dumpMethods() {
		for (String method : methods.keySet()) {
			System.out.println(method);
		}
	}
	
	public ScriptObject invoke(String method, Object...arguments) throws Exception {
		Method thisMethod = methods.get(method);
		if (thisMethod == null)
			throw new Exception("Method \""+method+"\" does not exist in module.");
		Parameter[] parameters = thisMethod.getParameters();
		if (parameters.length != arguments.length) {
			throw new Exception("Incorrect argument length for method \""+method+"\"");
		}
		for (int i = 0; i < parameters.length; i++) {
			if (!parameters[i].getType().equals(arguments[i].getClass())) {
				throw new Exception("Incorrect arguments for method \""+method+"\"");
			}
		}
		return new ScriptObject(thisMethod.invoke(containedObject, arguments));
	}
}
