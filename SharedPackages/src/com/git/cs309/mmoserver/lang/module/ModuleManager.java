package com.git.cs309.mmoserver.lang.module;

import java.util.HashMap;

import com.git.cs309.mmoserver.script.GlobalScriptVariable;
import com.git.cs309.mmoserver.script.JavaScriptEngine;

public final class ModuleManager extends GlobalScriptVariable {
	private static final ModuleManager INSTANCE = new ModuleManager();
	private static final HashMap<String, Module> moduleMap = new HashMap<>();
	private static String projectBase = "";
	
	private ModuleManager() {
		super(JavaScriptEngine.getScriptEngine());
	}
	
	public static final ModuleManager getInstance() {
		return INSTANCE;
	}
	
	public final Module getModule(final String moduleName) {
		if (moduleMap.containsKey(moduleName)) {
			return moduleMap.get(moduleName);
		}
		try {
			Module module = (Module) ClassLoader.getSystemClassLoader().loadClass(projectBase+moduleName).newInstance();
			moduleMap.put(moduleName, module);
			return module;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (Module) new Object();
	}

	@Override
	public String getVariableName() {
		return "ModuleManager";
	}
}
