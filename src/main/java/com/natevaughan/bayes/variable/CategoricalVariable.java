package com.natevaughan.bayes.variable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by nate on 1/30/17.
 */
public class CategoricalVariable implements Variable {

    private final String name;
    private final Collection<Value> values = new HashSet<>();
    private final Map<Variable, Double> affinityMap = new HashMap<>();

    public CategoricalVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<Value> getValues() {
        return values;
    }

    public Double getAffinity(Variable variable) {
        return affinityMap.get(variable);
    }
}
