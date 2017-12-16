package com.natevaughan.splitter;

/**
 * @author Nate Vaughan
 */
public class Fork {
	private final String variable;
	private final Double threshold;

	public Fork(String variable, Double threshold) {
		this.variable = variable;
		this.threshold = threshold;
	}

	public String getVariable() {
		return variable;
	}

	public Double getThreshold() {
		return threshold;
	}
}
