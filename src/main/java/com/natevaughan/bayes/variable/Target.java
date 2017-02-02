package com.natevaughan.bayes.variable;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Target {
    Variable getTargetVariable();
    Collection<Value> getPositiveValues();
    Collection<Variable> getRelevantVariables();
    boolean isPositive(Value value);
}