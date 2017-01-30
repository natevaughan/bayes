package com.natevaughan.bayes.variable;

/**
 * Created by nate on 1/30/17.
 */
public interface Value {
    Variable getVariable();
    String getName();
    Long getPositiveCount(Value value);
}
