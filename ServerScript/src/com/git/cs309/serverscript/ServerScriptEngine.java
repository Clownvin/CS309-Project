package com.git.cs309.serverscript;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.git.cs309.serverscript.core.Frame;
import com.git.cs309.serverscript.core.Script;
import com.git.cs309.serverscript.core.ScriptEnvironment;
import com.git.cs309.serverscript.parser.definitions.If;
import com.git.cs309.serverscript.parser.definitions.MethodInvocation;
import com.git.cs309.serverscript.parser.definitions.VariableStore;
import com.git.cs309.serverscript.types.ScriptObject;

public final class ServerScriptEngine {
	private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(20);
	private static final Frame BOTTOM_FRAME = new Frame(null);
	
	public static Frame getBottomFrame() {
		return BOTTOM_FRAME;
	}
	
	public static void run(final Script script, final Frame frame) {
		ScriptEnvironment environment = new ScriptEnvironment(script, frame);
		EXECUTOR_SERVICE.submit(environment);
	}
	
	public static void main(String[] args) throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		engine.put("a", new ScriptObject("loll"));
		engine.eval("a = new com.git.cs309.serverscript.types.ScriptObject(\"Hahaha\");");
		System.out.println(engine.eval("print( a.toString());"));
		System.out.println(new If("").matches("if ($lolg != $lol)"));
		System.out.println(new MethodInvocation("").matches("$lol.helloWorld()"));
		System.out.println(new VariableStore("").matches("$lol = $lol"));
		System.out.println(new VariableStore("").matches("$lol = $lol.get()"));
		System.out.println(new VariableStore("").matches("$lol = new Lol"));
	}
}
