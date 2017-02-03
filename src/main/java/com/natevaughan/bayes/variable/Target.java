package com.natevaughan.bayes.variable;

import com.natevaughan.bayes.dataset.Dataset;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Target {
    Variable getTargetVariable();
    Value getPositiveValue();
    Collection<Variable> getRelevantVariables();
    Long getPositiveCount();
    Long getNegativeCount();
    void incrementPositiveCount();
    void incrementNegativeCount();
    boolean isPositive(Value value);
    Dataset predict(Dataset dataset);
    void setRelevantVariables(Collection<Variable> variables);
    Double getEpsilon();
    void setEpsilon(Double epsilon);
}