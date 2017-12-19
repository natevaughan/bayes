package com.natevaughan.tree

import groovy.util.logging.Slf4j
import groovy.transform.TypeChecked
import org.junit.Test
import spock.lang.Specification

/**
 * @author Nate Vaughan
 */
@TypeChecked
@Slf4j
class NumericBinaryNodeSpec extends Specification {

	final static String TRUE = true.toString()
	final static String FALSE = false.toString()
	final static Double THRESHOLD = 2.0D

	@Test
	def "it should divide data and give calculations"(Map values, String expectedClass) {
		given:
		def positiveNode = new LeafNode(TRUE)
		def negativeNode = new LeafNode(FALSE)
		def fork = new NumericBinaryNode("foo", THRESHOLD, positiveNode, negativeNode)
		expect:
		def classification = fork.getClassification(values)
		classification == expectedClass
		where:
		values                 | expectedClass
		["foo": THRESHOLD + 1] | TRUE
		["foo": THRESHOLD - 1] | FALSE
		["foo": THRESHOLD]     | TRUE
	}
}
