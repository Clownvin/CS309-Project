package com.git.cs309.serverscript.parser.definitions;

import com.git.cs309.serverscript.parser.Node;

public abstract class Definition extends Node {
	
	public Definition(String fullNodeText) {
		super(fullNodeText);
	}
	
	public abstract Definition createNode();

	public abstract boolean matches(String string);
}
