package com.natevaughan.bayes.dataset;

import com.google.common.collect.Table;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Dataset {
    Table<Long, Variable, Value> getDataset();
    Target getTarget();
    Collection<Variable> getVariables();
}
