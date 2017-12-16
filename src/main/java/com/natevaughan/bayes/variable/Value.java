package com.natevaughan.bayes.variable;

import java.util.Map;

/**
 * @author Nate Vaughan
 */
public interface Value {
    Variable getVariable();
    String getName();
    Long getCount(Value value);
    Map<Value, Long> getCounts(Variable variable);
    void incrementCountFor(Value value);
}
