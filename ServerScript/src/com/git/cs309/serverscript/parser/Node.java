package com.git.cs309.serverscript.parser;

public class Node {
	protected final String fullNodeText;
	
	public Node(final String fullNodeText) {
		this.fullNodeText = fullNodeText;
	}
	
	public String getFullNodeText() {
		return fullNodeText;
	}
}
