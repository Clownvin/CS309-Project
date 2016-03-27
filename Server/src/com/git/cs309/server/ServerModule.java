package com.git.cs309.server;

import com.git.cs309.server.module.AbstractModule;
import com.git.cs309.server.module.OnStart;

public class ServerModule extends AbstractModule<String> {
	
	@OnStart
	public void startServer() {
		
	}

	@Override
	public String getKey() {
		return "ServerModule";
	}

}
