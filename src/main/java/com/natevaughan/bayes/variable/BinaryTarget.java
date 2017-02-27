package com.natevaughan.bayes.variable;

import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.Dataset;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nate on 2/1/17.
 */
public class BinaryTarget implements Target {

    private Double epsilon = 0.5;
    private final Variable targetVariable;
    private Map<Variable, Variable> relevantVariables = new HashMap<>();
    private Value positiveValue;
    private Long positiveCount = 0L;
    private Long negativeCount = 0L;

    public BinaryTarget(Variable targetVariable, Value positiveValue) {
        this.targetVariable = targetVariable;
        this.positiveValue = positiveValue;
    }

    public Variable getTargetVariable() {
        return targetVariable;
    }

    public Value getPositiveValue() {
        return positiveValue;
    }

    public Map<Variable, Variable> getRelevantVariables() {
        return relevantVariables;
    }

    public Long getPositiveCount() {
        return positiveCount;
    }

    public Long getNegativeCount() {
        return negativeCount;
    }

    public void incrementPositiveCount() {
        ++this.positiveCount;
    }

    public void incrementNegativeCount() {
        ++this.negativeCount;
    }

    public boolean isPositive(Value value) {
        return positiveValue.equals(value);
    }

    public PredictionValue predict(Collection<Value> values) {
        BigDecimal likelihoodPositive = new BigDecimal(1.0);
        BigDecimal likelihoodNegative = new BigDecimal(1.0);
        Integer wordcount = 0;
        for (Value value : values) {
            Variable relevant = this.relevantVariables.get(value.getVariable());
            if (relevant == null) {
                continue;
            } else {
                ++wordcount;
            }
            Value varVal = relevant.getValue(value);
            Map<Value, Long> counts = varVal.getCounts(targetVariable);
            Long positiveCount = 0L;
            Long negativeCount = 0L;
            for (Value val : counts.keySet()) {
                if (isPositive(val)) {
                    positiveCount += counts.get(val);
                } else {
                    negativeCount += counts.get(val);
                }
            }
            likelihoodPositive = likelihoodPositive.multiply(new BigDecimal(positiveCount.doubleValue() + epsilon));
            likelihoodNegative = likelihoodNegative.multiply(new BigDecimal(negativeCount.doubleValue() + epsilon));
        }
        likelihoodPositive = likelihoodPositive.divide(new BigDecimal(Math.pow(positiveCount.doubleValue(), (wordcount - 1))), 1000, RoundingMode.HALF_UP);
        likelihoodNegative = likelihoodNegative.divide(new BigDecimal(Math.pow(negativeCount.doubleValue(), (wordcount - 1))), 1000, RoundingMode.HALF_UP);
        return new PredictionValue(likelihoodPositive, likelihoodNegative, targetVariable);
    }

    public void setRelevantVariables(Collection<Variable> variables) {
        this.relevantVariables = new HashMap<>();
        for (Variable var : variables) {
            relevantVariables.put(var, var);
        }
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }
}
