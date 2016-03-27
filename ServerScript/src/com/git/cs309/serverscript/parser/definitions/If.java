package com.git.cs309.serverscript.parser.definitions;

import java.util.regex.Pattern;

public class If extends Definition {
	private static final Pattern ifRegex = Pattern.compile("(if)\\s*\\((\\$\\w+)\\s((==)|(!=)|(>=)|(<=))\\s(\\$\\w+)\\)");
	
	@Override
	public boolean matches(String string) {
		return ifRegex.matcher(string).matches();
	}
	
}
