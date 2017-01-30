package com.natevaughan.bayes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nate on 1/14/17.
 */
public class DataCounter {

    private final Target target;
    private final Integer targetPositionInHeader;
    private final Integer fieldCount;
    private Long datasetSize;
    private final Map<Integer, String> headerPositionMap;
    private final Map<String, Variable> variableMap;
    private final Map<String, String> classNameMap;

    private DataCounter(Map<Integer, String> headerPositionMap, Map<String, String> classMap, Map<String, Variable> variableMap, Target target, Integer targetPositionInHeader) {
        this.target = target;
        this.targetPositionInHeader = targetPositionInHeader;
        this.headerPositionMap = headerPositionMap;
        this.classNameMap = classMap;
        this.variableMap = variableMap;
        this.fieldCount = headerPositionMap.values().size();
    }

    public static DataCounter create(String[] header, String[] classes, Target target) throws DataCounterException {
        Map<Integer, String> headerPositionMap = new HashMap<>();
        Map<String, String> classMap = new HashMap<>();
        Map<String, Variable> variableMap = new HashMap<>();
        Integer targetPositionInHeader = null;
        for (int i = 0; i < header.length; ++i) {
            headerPositionMap.put(i, header[i]);
            classMap.put(header[i], classes[i]);
            variableMap.put(header[i], new Variable(header[i], target));
            if (target.getName().equals(header[i])) {
                targetPositionInHeader = i;
                target.setTargetVariable(variableMap.get(header[i]));
            }
        }
        if (targetPositionInHeader == null) {
            throw new DataCounterException("Could not find target among header columns: " + target.getName());
        }
        return new DataCounter(Collections.unmodifiableMap(headerPositionMap), Collections.unmodifiableMap(classMap), Collections.unmodifiableMap(variableMap), target, targetPositionInHeader);
    }

    public void process(String[][] rows) throws DataCounterException {
        this.datasetSize = new Long(rows.length);
        for (int i = 0; i < datasetSize; ++i) {
            String[] row = rows[i];

            if (!fieldCount.equals(row.length)) {
                throw new DataCounterException("Bad row: " + i);
            }

            boolean rowIsTargetValue = false;

            if (target.getTargetValue().equals(row[targetPositionInHeader])) {
                rowIsTargetValue = true;
            }

            for (int j = 0; j < row.length; ++j) {
                Variable v = variableMap.get(headerPositionMap.get(j));
                if (v.getCountsNegative().get(row[j]) == null || v.getCountsPositive().get(row[j]) == null) {
                    v.getCountsNegative().put(row[j], 0L);
                    v.getCountsPositive().put(row[j], 0L);
                }

                if (rowIsTargetValue) {
                    v.getCountsPositive().put(row[j], (Long) v.getCountsPositive().get(row[j]) + 1);
                } else {
                    v.getCountsNegative().put(row[j], (Long) v.getCountsNegative().get(row[j]) + 1);
                }
            }
        }
    }

    public Target getTarget() {
        return target;
    }

    public Map<Integer, String> getHeaderPositionMap() {
        return headerPositionMap;
    }

    public Map<String, Variable> getVariableMap() {
        return variableMap;
    }

    public boolean getPrediction(Tuple2[] values) {
        Double likelihoodPositive = 1.0;
        Double likelihoodNegative = 1.0;

        Long positive;
        Long negative;

        for (int i = 0; i < values.length; ++i) {
            Variable var = variableMap.get(values[i].getKey());
            if (var.getEffectiveness() < 0.7) {
                continue;
            }
            positive = (Long) var.getCountsPositive().get(values[i].getValue());
            negative = (Long) var.getCountsNegative().get(values[i].getValue());
            likelihoodPositive *= ((double) positive) / (positive + negative);
            likelihoodNegative *= ((double) negative) / (positive + negative);
        }
        positive = (Long) variableMap.get(target.getName()).getCountsPositive().get(target.getTargetValue());
        negative = datasetSize - positive;
        likelihoodPositive *= ((double) positive) / (datasetSize);
        likelihoodNegative *= ((double) negative) / (datasetSize);

        return likelihoodPositive > likelihoodNegative;
    }
}
