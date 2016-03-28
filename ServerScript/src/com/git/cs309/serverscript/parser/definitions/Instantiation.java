package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

import com.git.cs309.serverscript.parser.NodeType;

public abstract class Instantiation extends Definition {
	
	public Instantiation(String fullNodeText) {
		super(fullNodeText);
	}

	private final static String PATTERN_STRING = "\\s*new\\s+(\\w\\.?)+";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.NOT_PARSED;
	}

}
