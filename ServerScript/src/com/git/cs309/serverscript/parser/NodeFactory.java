package com.git.cs309.serverscript.parser;

import com.git.cs309.serverscript.parser.definitions.MethodInvocation;
import com.git.cs309.serverscript.parser.definitions.VariableStore;

public final class NodeFactory {
	
	public static final Node createNode(String line) {
		if (new MethodInvocation("").matches(line)) {
			return new MethodInvocation(line).createNode();
		} else if (new VariableStore("").matches(line)) {
			return new VariableStore(line).createNode();
		}
		throw new RuntimeException("No node for line: "+line);
	}
}
