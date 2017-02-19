package com.natevaughan.bayes.variable;

import java.util.Map;

/**
 * Created by nate on 1/30/17.
 */
public interface Variable {
    String getName();
    Map<Value, Value> getValues();
    Value getValue(Value value);
    void addValue(Value value);
    Double getAffinity(Variable variable);
}