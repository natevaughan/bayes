package com.natevaughan.bayes.variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nate on 1/30/17.
 */
public class CategoricalValue implements Value {

    public final String name;
    public final CategoricalVariable variable;
    Map<Value, Long> positiveCountMap = new HashMap<>();
    Map<Value, Long> negativeCountMap = new HashMap<>();

    public CategoricalValue(String name, CategoricalVariable variable) {
        this.name = name;
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    public String getName() {
        return name;
    }

    public Long getPositiveCount(Value value) {
        return positiveCountMap.get(value);
    }

    public Long getNegativeCount(Value value) {
        return negativeCountMap.get(value);
    }

    public synchronized void incrementPositiveCountFor(Value value) {
        positiveCountMap.put(value, getPositiveCount(value) + 1);
    }

    public synchronized void incrementNegativeCountFor(Value value) {
        negativeCountMap.put(value, getNegativeCount(value) + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoricalValue that = (CategoricalValue) o;

        if (!name.equals(that.name)) return false;
        return variable.equals(that.variable);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + variable.hashCode();
        return result;
    }
}
