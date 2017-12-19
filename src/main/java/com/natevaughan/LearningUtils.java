package com.natevaughan;

import javafx.collections.transformation.SortedList;
import java.util.Collections;
import java.util.List;

/**
 * @author Nate Vaughan
 */
public class LearningUtils {

	/**
	 * Finds the mean of two numbers
	 *
	 * @param first double
	 * @param second double
	 * @return the mathematical mean
	 */
	public static double mean(double first, double second) {
		return (first + second) / 2;
	}

	/**
	 * Finds the median of a list of Doubles, allowing the caller to specify
	 * whether the list has been sorted
	 *
	 * @param doubles the list process
	 * @param sort whether the list has already been sorted
	 * @return Double median value
	 */
	public static Double median(List<Double> doubles, Boolean sort) {
		Integer size = doubles.size();
		if (size < 1) {
			throw new IllegalArgumentException("Cannot process empty list");
		}
		if (sort && !(doubles instanceof SortedList)) {
			Collections.sort(doubles);
		}
		Integer middle = (size - 1) / 2;
		Double lower = doubles.get(middle);
		Double upper = doubles.get(size - (middle + 1));
		return mean(lower, upper);
	}

	/**
	 * Finds the median of a list of unsorted Doubles
	 * @param doubles the list process
	 * @return Double median value
	 */
	public static Double median(List<Double> doubles) {
		return median(doubles, true);
	}

	/**
	 * Calculates entropy of a subset with a given parent count
	 * @param count1 the count in the subset where the target is true
	 * @param count2 the count in the subset where the target is false
	 * @param parentCount the total number of observations of parent set
	 * @return double entropy
	 */
	public static double entropyWithParentCount(long count1, long count2, long parentCount) {
		if (count1 == 0l && count2 == 0l) {
			return 0.0;
		}
		return (double) (count1 + count2) / parentCount * entropy(count1, count2);
	}

	/**
	 * Entropy with counts
	 * @param count1 count for target
	 * @param count2 count against target
	 * @return double entropy
	 */
	public static double entropy(long count1, long count2) {
		if (count1 == 0l && count2 == 0l) {
			return 0;
		}
		return entropy((double) count1 / (count1 + count2)) + entropy((double) count2 / (count1 + count2));
	}

	/**
	 * Calculates entropy of a given probability
	 * @param probability double
	 * @return double entropy
	 */
	public static double entropy(double probability) {
		if (probability == 0.0) {
			return 0.0;
		}
		return -1 * probability * Math.log(probability) / Math.log(2);
	}
}
