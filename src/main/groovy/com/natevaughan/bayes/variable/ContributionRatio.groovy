package com.natevaughan.bayes.variable;

/**
 * Created by nate on 3/1/17.
 */
class ContributionRatio implements Comparable<ContributionRatio>{
    Value value
    Double ratio

    int compareTo(ContributionRatio o) {
        return ratio.compareTo(o.ratio)
    }

    String toString() {
        return value.variable.name + " (" + ratio.round(3) + ")"
    }
}
