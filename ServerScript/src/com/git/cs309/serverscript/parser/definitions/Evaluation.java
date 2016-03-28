package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

import com.git.cs309.serverscript.parser.NodeType;

public abstract class Evaluation extends Definition {
	
	public Evaluation(String fullNodeText) {
		super(fullNodeText);
	}

	private final Pattern evaluationPattern = Pattern.compile("("+Variable.getPatternString()+"\\s((==)|(!=)|(>=)|(<=))\\s(\\$\\w+))");
	
	@Override
	public boolean matches(String string) {
		return evaluationPattern.matcher(string).matches();
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.NOT_PARSED;
	}
	
}
