package com.natevaughan.bayes.dataset;

import com.natevaughan.bayes.variable.Variable;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Dataset {
    Collection<Variable> getVariables();
}
