package com.natevaughan

import groovy.util.logging.Slf4j
import spock.lang.Specification

import static LearningUtils.median
import static com.natevaughan.LearningUtils.entropyWithParentCount

/**
 * @author Nate Vaughan
 */
@Slf4j
class LearningUtilsSpec extends Specification {

	def "it should find the median"(List<Double> doubleList, Double mid) {
		expect:
		Double result = median(doubleList)
		mid == result

		where:
		doubleList       | mid
		[1D]             | 1D
		[1D, 2D]         | 1.5D
		[1D, 2D, 3D]     | 2D
		[2D, 1D, 3D, 4D] | 2.5D
		[4D, 2D, 1D, 3D] | 2.5D
	}

	def "entropy test"(long count1, long count2, long parentSetCount, double expectedEntropy) {
		expect:
		double entr = entropyWithParentCount(count1, count2, parentSetCount)
		Math.abs(entr - expectedEntropy) < 0.001D

		where:
		count1 | count2 | parentSetCount | expectedEntropy
		4	   | 0		| 14			 | 0.0D
		5	   | 9		| 14			 | 0.94D
		3	   | 2		| 5				 | 0.971D
	}
}
