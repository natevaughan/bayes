package com.natevaughan.bayes.variable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nate Vaughan
 */
public class LinesToValuesProcessor {

	public static Collection<Value> convert(String line) {
		return convert(line.split(" "));
	}

	public static Collection<Value> convert(String[] words) {
		return convert(Arrays.asList(words));
	}

	public static Collection<Value> convert(Collection<String> words) {
		Set<Value> values = new HashSet<>();
		List<String> excluded = Arrays.asList("a", "an", "and", "the");
		for (String word : words) {
			if (excluded.contains(word)) {
				continue;
			}
			values.add(new BooleanValue(true, new CategoricalVariable(word)));
		}
		return values;
	}

	public static Collection<Value> stripNonAlphaAndConvert(String line) {
		return convert(line.replaceAll("[^A-Za-z0-9 ]", ""));
	}
}
