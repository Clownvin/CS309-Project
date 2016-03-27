package com.git.cs309.server.module;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.git.cs309.server.util.Keyed;

public abstract class AbstractModule<Key> implements Keyed<Key>{
	protected final HashMap<String, Method> methods = new HashMap<>();
	protected final HashMap<String, String> methodParameters = new HashMap<>();
	protected final List<Method> closingMethods = new ArrayList<>();
	
	public AbstractModule() {
		
	}
	
	public void invoke(String method, Object...arguments) throws Exception {
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
		thisMethod.invoke(this, arguments);
	}
	
	public final void stop() {
		for (Method method : closingMethods) {
			try {
				method.invoke(this, new Object[] {});
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public final void start() {
		for (Method method : this.getClass().getMethods()) {
			if (method.isAnnotationPresent(ModuleMethod.class)) {
				String methodName = method.getAnnotation(ModuleMethod.class).name();
				String methodParameters = method.getAnnotation(ModuleMethod.class).parameters();
				methods.put(methodName, method);
				this.methodParameters.put(methodName, methodParameters);
			}
			if (method.isAnnotationPresent(OnStart.class)) {
				try {
					method.invoke(this, new Object[] {});
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			if (method.isAnnotationPresent(OnClose.class)) {
				closingMethods.add(method);
			}
		}
	}
}
