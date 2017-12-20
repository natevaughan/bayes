package com.natevaughan.tree

import spock.lang.Specification;

/**
 * @author Nate Vaughan
 */
class CategoricalNodeSpec extends Specification {

	private final String NODE_NAME = "foo"
	private final String NODE_VALUE_TRUE = "value.true"
	private final String NODE_VALUE_FALSE = "value.false"

	def "it should return categorical values"() {
		given:
		Map<String, Node> nodeMap = [:]
		nodeMap.put(NODE_VALUE_TRUE, new LeafNode(NODE_VALUE_TRUE))
		nodeMap.put(NODE_VALUE_FALSE, new LeafNode(NODE_VALUE_FALSE))
		CategoricalNode node = new CategoricalNode(NODE_NAME, nodeMap)
		when:
		Map<String, Object> values = [:]
		values.put(NODE_NAME, NODE_VALUE_TRUE)
		String classificaion = node.getClassification(values)
		then:
		classificaion == NODE_VALUE_TRUE
	}
}
