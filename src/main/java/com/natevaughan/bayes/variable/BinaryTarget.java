package com.natevaughan.bayes.variable;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Nate Vaughan
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

        Set<ContributionRatio> contributionRatios = new TreeSet<>();

        BigDecimal likelihoodPositive = new BigDecimal(positiveCount.doubleValue() / (positiveCount + negativeCount));
        BigDecimal likelihoodNegative = new BigDecimal(negativeCount.doubleValue() / (positiveCount + negativeCount));

        for (Value value : values) {
            Variable relevant = this.relevantVariables.get(value.getVariable());
            if (relevant == null) {
                continue;
            }

            Value varVal = relevant.getValue(value);
            Map<Value, Long> counts = varVal.getCounts(targetVariable);
            Long positiveValCount = 0L;
            Long negativeValCount = 0L;
            for (Value val : counts.keySet()) {
                if (isPositive(val)) {
                    positiveValCount += counts.get(val);
                } else {
                    negativeValCount += counts.get(val);
                }
            }

            Double positiveRatio = (positiveValCount.doubleValue() + epsilon) / (positiveCount + epsilon);
            Double negativeRatio = (negativeValCount.doubleValue() + epsilon) / (negativeCount + epsilon);

            ContributionRatio ratio = new ContributionRatio(value, (positiveRatio)/(negativeRatio));
            contributionRatios.add(ratio);

            likelihoodPositive = likelihoodPositive.multiply(new BigDecimal(positiveRatio));
            likelihoodNegative = likelihoodNegative.multiply(new BigDecimal(negativeRatio));
        }

        return new PredictionValue(likelihoodPositive, likelihoodNegative, targetVariable, contributionRatios);
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
