package com.natevaughan.bayes.variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nate on 2/1/17.
 */
public class BinaryTarget implements Target {

    private final Variable targetVariable;
    private List<Value> positiveValues = new ArrayList<>();

    public BinaryTarget(Variable targetVariable) {
        this.targetVariable = targetVariable;
    }

    public Variable getTargetVariable() {
        return null;
    }

    public Collection<Value> getPositiveValues() {
        return null;
    }

    public Collection<Variable> getRelevantVariables() {
        return null;
    }

    public boolean isPositive(Value value) {
        return this.positiveValues.contains(value);
    }
}
