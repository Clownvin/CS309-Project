package com.git.cs309.serverscript.parser;

import com.git.cs309.serverscript.core.ScriptEnvironment;
import com.git.cs309.serverscript.core.action.Action;

public abstract class Node {
	private Node previousNode;
	private Node nextNode;
	private Node innerNode;
	protected final String fullNodeText;
	
	public Node(final String fullNodeText) {
		this.fullNodeText = fullNodeText;
	}
	
	public String getFullNodeText() {
		return fullNodeText;
	}
	
	public abstract NodeType getNodeType();

	/**
	 * @return the previousNode
	 */
	public Node getPreviousNode() {
		return previousNode;
	}
	
	public abstract Action getAction(ScriptEnvironment environment);

	/**
	 * @param previousNode the previousNode to set
	 */
	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	/**
	 * @return the nextNode
	 */
	public Node getNextNode() {
		return nextNode;
	}

	/**
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * @return the innerNode
	 */
	public Node getInnerNode() {
		return innerNode;
	}

	/**
	 * @param innerNode the innerNode to set
	 */
	public void setInnerNode(Node innerNode) {
		this.innerNode = innerNode;
	}
}
