package com.natevaughan.bayes.variable;

import java.util.Map;

/**
 * @author Nate Vaughan
 */
public interface Variable {
    String getName();
    Map<Value, Value> getValues();
    Value getValue(Value value);
    void addValue(Value value);
    Double getAffinity(Variable variable);
}