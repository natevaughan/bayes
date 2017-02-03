package com.natevaughan.bayes.variable;

import java.util.Map;

/**
 * Created by nate on 1/30/17.
 */
public interface Value {
    Variable getVariable();
    String getName();
    Long getCount(Value value);
    Map<Value, Long> getCounts(Variable variable);
    void incrementCountFor(Value value);
}
