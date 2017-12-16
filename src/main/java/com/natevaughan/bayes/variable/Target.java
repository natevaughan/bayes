package com.natevaughan.bayes.variable;

import java.util.Collection;
import java.util.Map;

/**
 * @author Nate Vaughan
 */
public interface Target {
    Variable getTargetVariable();
    void incrementPositiveCount();
    void incrementNegativeCount();
    boolean isPositive(Value value);
    PredictionValue predict(Collection<Value> values);
    Map<Variable, Variable> getRelevantVariables();
    void setRelevantVariables(Collection<Variable> variables);
    Double getEpsilon();
    void setEpsilon(Double epsilon);
    Long getPositiveCount();
    Long getNegativeCount();
}