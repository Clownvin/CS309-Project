package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

public class Variable extends Definition {
	public static final String PATTERN_STRING = "\\$\\w+";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}
	
	@Override
	public boolean matches(String string) {
		return Pattern.compile(PATTERN_STRING).matcher(string).matches();
	}

}
