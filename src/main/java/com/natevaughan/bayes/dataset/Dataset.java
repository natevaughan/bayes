package com.natevaughan.bayes.dataset;

import com.google.common.collect.Table;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;

import java.util.Collection;

/**
 * @author Nate Vaughan
 */
public interface Dataset {
    Table<Long, Variable, Value> getDataset();
    Target getTarget();
    Collection<Variable> getVariables();
}
