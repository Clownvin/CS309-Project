package com.git.cs309.serverscript;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.git.cs309.serverscript.core.Frame;
import com.git.cs309.serverscript.core.Script;
import com.git.cs309.serverscript.core.ScriptEnvironment;
import com.git.cs309.serverscript.parser.Node;
import com.git.cs309.serverscript.parser.definitions.Evaluation;
import com.git.cs309.serverscript.parser.definitions.If;
import com.git.cs309.serverscript.parser.definitions.MethodInvocation;
import com.git.cs309.serverscript.parser.definitions.VariableStore;

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
	
	public static void main(String[] args) {
		System.out.println(new If().matches("if ($lolg != $lol)"));
		System.out.println(new Evaluation().matches("$lol == $lol"));
		System.out.println(new MethodInvocation().matches("$lol.helloWorld()"));
		System.out.println(new VariableStore().matches("$lol = $lol"));
	}
}
