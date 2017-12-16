package com.natevaughan.bayes.variable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * @author Nate Vaughan
 */
public abstract class AbstractValue implements Value {

    Table<Variable, Value, Long> counts = HashBasedTable.create();

    public Long getCount(Value value) {
        return counts.get(value.getVariable(), value);
    }

    public synchronized void incrementCountFor(Value value) {
        Long positiveCount = getCount(value);
        if (positiveCount == null) {
            counts.put(value.getVariable(), value, 1L);
        } else {
            counts.put(value.getVariable(), value, positiveCount + 1);
        }
    }

    public Map<Value, Long> getCounts(Variable  variable) {
        return counts.row(variable);
    }

}
