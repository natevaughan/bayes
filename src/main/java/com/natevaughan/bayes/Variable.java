package com.natevaughan.bayes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nate on 1/14/17.
 */
public class Variable<T> {
    private final String name;
    private final Map<T, Long> countsPositive = new HashMap<T, Long>();
    private final Map<T, Long> countsNegative = new HashMap<T, Long>();
    private final Target target;

    public Variable(String name, Target target) {
        this.name = name;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public Map<T, Long> getCountsPositive() {
        return countsPositive;
    }

    public Map<T, Long> getCountsNegative() {
        return countsNegative;
    }

    public Double getEffectiveness() {
        Long correct = 0L;
        Long incorrect = 0L;
        Long positive;
        Long negative;
        for (Object o : countsPositive.keySet()) {
            positive = countsPositive.get(o);
            negative = countsNegative.get(o);
            if (positive > negative) {
                correct += positive;
                incorrect += negative;
            } else {
                correct += negative;
                incorrect += positive;
            }
        }
        return ((double) correct) / (correct + incorrect);
    }
}
