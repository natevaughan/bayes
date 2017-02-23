package com.natevaughan.bayes.variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nate on 1/30/17.
 */
public class CategoricalVariable implements Variable {

    private final String name;
    private final Map<Value, Value> values = new HashMap<>();
    private final Map<Variable, Double> affinityMap = new HashMap<>();

    public CategoricalVariable(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public Map<Value, Value> getValues() {
        return values;
    }

    public Value getValue(Value value) {
        return values.get(value);
    }

    public void addValue(Value value) {
        this.values.put(value, value);
    }

    public Double getAffinity(Variable variable) {
        return affinityMap.get(variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoricalVariable that = (CategoricalVariable) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
