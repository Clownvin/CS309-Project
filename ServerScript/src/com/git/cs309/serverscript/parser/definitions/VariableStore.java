package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

import com.git.cs309.serverscript.core.ScriptEnvironment;
import com.git.cs309.serverscript.core.action.Action;
import com.git.cs309.serverscript.parser.NodeType;

public class VariableStore extends Definition {
	
	public VariableStore(String fullNodeText) {
		super(fullNodeText);
	}

	private static final String PATTERN_STRING = Variable.getPatternString()+"\\s*=\\s*(("+Variable.getPatternString()+")|("+MethodInvocation.getPatternString()+")|("+Instantiation.getPatternString()+"))";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}
	
	@Override
	public boolean matches(String string) {
		return Pattern.compile(PATTERN_STRING).matcher(string).matches();
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.VARIABLE_STORE;
	}

	@Override
	public Definition createNode() {
		
		return this;
	}

	@Override
	public Action getAction(ScriptEnvironment environment) {
		// TODO Auto-generated method stub
		return null;
	}

}
