package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

public class Evaluation extends Definition {
	private final Pattern evaluationPattern = Pattern.compile("(\\$\\w+)\\s((==)|(!=)|(>=)|(<=))\\s(\\$\\w+)|()");
	
	@Override
	public boolean matches(String string) {
		return evaluationPattern.matcher(string).matches();
	}
	
}
