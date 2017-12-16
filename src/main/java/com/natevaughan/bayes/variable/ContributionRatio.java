package com.natevaughan.bayes.variable;

/**
 * @author Nate Vaughan
 */
public class ContributionRatio implements Comparable<ContributionRatio>{
	private final Value value;
	private final Double ratio;

	ContributionRatio(Value value, Double ratio) {
		this.value = value;
		this.ratio = ratio;
	}

	@Override
	public int compareTo(ContributionRatio o) {
		return ratio.compareTo(o.ratio);
	}

	@Override
	public String toString() {
		return value.getVariable().getName() + " (" + ratio + ")";
	}

	public Value getValue() {
		return value;
	}

	public Double getRatio() {
		return ratio;
	}
}
