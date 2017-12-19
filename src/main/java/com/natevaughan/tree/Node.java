package com.natevaughan.tree;

import java.util.Map;

/**
 * Created by nate on 12/17/17
 */
public interface Node {
	String getClassification(Map<String, Double> values);
}
