package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

public class VariableStore extends Definition {
	private static final String PATTERN_STRING = Variable.getPatternString()+"\\w*=\\w*(("+Variable.getPatternString()+")|("+MethodInvocation.getPatternString()+"))";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}
	
	@Override
	public boolean matches(String string) {
		return Pattern.compile(PATTERN_STRING).matcher(string).matches();
	}

}
