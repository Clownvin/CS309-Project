package com.git.cs309.mmoserver.script;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class JavaScriptEngine {
	private static final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
	
	private JavaScriptEngine() {
		
	}
	
	public static final Object runScript(final String scriptPath) throws FileNotFoundException, ScriptException {
		return scriptEngine.eval(new FileReader(scriptPath));
	}
	
	public static final Object runStringScript(final String script) throws ScriptException {
		return scriptEngine.eval(script);
	}
	
	public static final void setVariable(final String variable, final Object object) {
		scriptEngine.put(variable, object);
	}
	
	public static final ScriptEngine getScriptEngine() {
		return scriptEngine;
	}
}
