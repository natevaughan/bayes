package com.natevaughan.bayes.variable;

/**
 * Created by nate on 1/30/17.
 */
public interface Value {
    public Variable getVariable();
    public String getName();
    public Long getPositiveCount(Value value);
}
