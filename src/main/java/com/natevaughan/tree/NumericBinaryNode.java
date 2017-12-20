package com.natevaughan.tree;

import java.util.Map;

/**
 * @author Nate Vaughan
 */
public class NumericBinaryNode implements Node {
	private final String variable;
	private final Double threshold;
	private final Node positiveNode;
	private final Node negativeNode;

	public NumericBinaryNode(String variable, Double threshold, Node positiveNode, Node negativeNode) {
		this.variable = variable;
		this.threshold = threshold;
		this.positiveNode = positiveNode;
		this.negativeNode = negativeNode;
	}

	public String getVariable() {
		return variable;
	}

	public Double getThreshold() {
		return threshold;
	}

	@Override
	public String getClassification(Map<String, Object> values) {
		Double value = (Double) values.get(this.variable);
		if (value >= threshold) {
			return positiveNode.getClassification(values);
		} else {
			return negativeNode.getClassification(values);
		}
	}
}
