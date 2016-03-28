package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

import com.git.cs309.serverscript.parser.NodeType;

public abstract class Variable extends Definition {
	
	public Variable(String fullNodeText) {
		super(fullNodeText);
	}

	public static final String PATTERN_STRING = "\\$\\w+";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}
	
	@Override
	public boolean matches(String string) {
		return Pattern.compile(PATTERN_STRING).matcher(string).matches();
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.NOT_PARSED;
	}

}
