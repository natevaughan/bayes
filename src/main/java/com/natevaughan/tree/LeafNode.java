package com.natevaughan.tree;

import java.util.Map;

/**
 * Created by nate on 12/19/17
 */
public class LeafNode implements Node {

	private final String classification;

	public LeafNode(String classification) {
		this.classification = classification;
	}

	@Override
	public String getClassification(Map<String, Object> values) {
		return classification;
	}
}
