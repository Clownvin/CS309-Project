package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

public final class Arguments extends Definition {
	private static final String PATTERN_STRING = "((\\w\\$\\w+(\\,)?)+)?";
	
	public static String getPatternString() {
		return PATTERN_STRING;
	}
	@Override
	public boolean matches(String string) {
		return Pattern.compile(PATTERN_STRING).matcher(string).matches();
	}

}
