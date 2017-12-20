package com.natevaughan.tree;

import java.util.Map;

/**
 * Created by nate on 12/19/17
 */
public class CategoricalNode implements Node {

	private final String variable;
	private final Map<String, Node> nodeMap;

	public CategoricalNode(String name, Map<String, Node> nodeMap) {
		this.variable = name;
		this.nodeMap = nodeMap;
	}

	@Override
	public String getClassification(Map<String, Object> values) {
		String value = (String) values.get(variable);
		return nodeMap.get(value).getClassification(values);
	}
}
