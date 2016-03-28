package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

import com.git.cs309.serverscript.parser.NodeType;

public final class MethodInvocation extends Definition {
	public MethodInvocation(String fullNodeText) {
		super(fullNodeText);
	}

	private static final String PATTERN_STRING = "("+Variable.getPatternString()+"\\.\\w+\\("+Arguments.getPatternString()+"\\))";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}
	
	@Override
	public boolean matches(String string) {
		return Pattern.compile(PATTERN_STRING).matcher(string).matches();
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.METHOD_INVOCATION;
	}

	@Override
	public Definition createNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
