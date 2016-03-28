package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

import com.git.cs309.serverscript.core.ScriptEnvironment;
import com.git.cs309.serverscript.core.action.Action;
import com.git.cs309.serverscript.parser.NodeType;

public class If extends Definition {
	
	public If(String fullNodeText) {
		super(fullNodeText);
	}

	private static final Pattern ifRegex = Pattern.compile("(if)\\s*\\((("+Variable.getPatternString()+"\\s*((==)|(!=)|(>=)|(<=))\\s*"+Variable.getPatternString()+")|("+MethodInvocation.getPatternString()+"))\\)");
	
	@Override
	public boolean matches(String string) {
		return ifRegex.matcher(string).matches();
	}

	@Override
	public NodeType getNodeType() {
		return NodeType.NOT_PARSED;
	}

	@Override
	public Definition createNode() {
		If ifNode = new If(fullNodeText);
		// TODO Auto-generated method stub
		return ifNode;
	}

	@Override
	public Action getAction(ScriptEnvironment environment) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
