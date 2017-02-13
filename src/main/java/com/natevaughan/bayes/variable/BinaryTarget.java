package com.natevaughan.bayes.variable;

import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.Dataset;
import groovy.lang.Tuple2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by nate on 2/1/17.
 */
public class BinaryTarget implements Target {

    private Double epsilon = 0.5;
    private final Variable targetVariable;
    private Collection<Variable> relevantVaribales = new HashSet<>();
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

    public Collection<Variable> getRelevantVariables() {
        return relevantVaribales;
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

    public boolean predict(Collection<Tuple2<Variable, Value>> values) {
        // XXX todo
        return false;
    }

    public Dataset predict(Dataset dataset) {
        Table<Long, Variable, Value> table = dataset.getDataset();
        for (Long rowId : table.rowKeySet()) {
            Double likelihoodPositive = 1.0;
            Double likelihoodNegative = 1.0;
            for (Variable var : relevantVaribales) {
                Value tbaleVal = table.get(rowId, var);
                Value varVal = var.getValue(tbaleVal);
                Map<Value, Long> counts = varVal.getCounts(targetVariable);
                Long positiveCount = 0L;
                Long negativeCount = 0L;
                for (Value value : counts.keySet()) {
                    if (isPositive(value)) {
                        positiveCount += counts.get(value);
                    } else {
                        negativeCount += counts.get(value);
                    }
                }
                likelihoodPositive *= (positiveCount.doubleValue() + epsilon);
                likelihoodNegative *= (negativeCount.doubleValue() + epsilon);
            }
            likelihoodPositive /= Math.pow(positiveCount.doubleValue(), (relevantVaribales.size() - 1));
            likelihoodNegative /= Math.pow(negativeCount.doubleValue(), (relevantVaribales.size() - 1));
            table.put(rowId, targetVariable,  new PredictionValue(likelihoodPositive, likelihoodNegative, targetVariable));
        }
        return dataset;
    }

    public void setRelevantVariables(Collection<Variable> variables) {
        this.relevantVaribales = variables;
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }
}
