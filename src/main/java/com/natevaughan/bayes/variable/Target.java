package com.natevaughan.bayes.variable;

import com.natevaughan.bayes.dataset.Dataset;
import groovy.lang.Tuple2;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Target {
    Variable getTargetVariable();
    void incrementPositiveCount();
    void incrementNegativeCount();
    boolean isPositive(Value value);
    Dataset predict(Dataset dataset);
    boolean predict(Collection<Tuple2<Variable, Value>> values);
    Collection<Variable> getRelevantVariables();
    void setRelevantVariables(Collection<Variable> variables);
    Double getEpsilon();
    void setEpsilon(Double epsilon);
}