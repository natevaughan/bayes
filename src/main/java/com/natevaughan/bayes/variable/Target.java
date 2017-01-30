package com.natevaughan.bayes.variable;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Target {
    public Variable getTargetVariable();
    public Collection<Variable> getRelevantVariables();
}