package com.git.cs309.server.module;

import java.util.Collection;
import java.util.HashMap;

public final class ModuleManager<Key> {
	private final HashMap<Key, AbstractModule<Key>> moduleMap = new HashMap<>();
	private String moduleBase;
	
	public ModuleManager(String projectBase) {
		this.moduleBase = projectBase;
	}
	
	@SuppressWarnings("unchecked")
	public AbstractModule<Key> startModule(Key moduleKey) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (moduleMap.containsKey(moduleBase+moduleKey)) {
			throw new IllegalStateException("Module with key \""+moduleKey.toString()+"\" already exists.");
		}
		Class<? extends AbstractModule<Key>> moduleClass = (Class<? extends AbstractModule<Key>>) ClassLoader.getSystemClassLoader().loadClass(moduleKey.toString());
		AbstractModule<Key> module = moduleClass.newInstance();
		moduleMap.put(moduleKey, module);
		module.start();
		return module;
	}
	
	public void setModuleBase(String moduleBase) {
		this.moduleBase = moduleBase;
	}
	
	public AbstractModule<Key> getModule(Key moduleKey) {
		if (!moduleMap.containsKey(moduleKey)) {
			try {
				startModule(moduleKey);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (moduleMap.containsKey(moduleKey)) {
			return moduleMap.get(moduleKey);
		}
		throw new IllegalStateException("Module with key \""+moduleKey.toString()+"\" doesn't exist.");
	}
	
	public ModuleManager() {
		this.moduleBase = "";
	}
	
	public ModuleManager(Collection<Key> autoStartKeys) {
		this.moduleBase = "";
		for (Key key : autoStartKeys) {
			try {
				startModule(key);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ModuleManager(Collection<Key> autoStartKeys, String projectBase) {
		this.moduleBase = projectBase;
		for (Key key : autoStartKeys) {
			try {
				startModule(key);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
