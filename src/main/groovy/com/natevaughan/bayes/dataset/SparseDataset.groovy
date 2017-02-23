package com.natevaughan.bayes.dataset

import com.google.common.collect.Table
import com.natevaughan.bayes.exception.BadRowException
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/**
 * Created by nate on 2/12/17.
 */
@CompileStatic
class SparseDataset implements Dataset {

    private final Target target
    private final List<Tuple2<Value, Collection<Value>>> data = []

    SparseDataset(Target target) {
        this.target = target
        this.data = []
    }

    Target getTarget() {
        target
    }

    Collection<Variable> getVariables() {
        target.relevantVariables.keySet()
    }

    void train(Value targetVal, Collection<Value> row) {
        if (targetVal == null || targetVal?.getVariable() != target.targetVariable) {
            throw new BadRowException("Cannot train on row with mismatched target")
        }
        if (target.isPositive(targetVal)) {
            target.incrementPositiveCount()
        } else {
            target.incrementNegativeCount()
        }

        for (Value value : row) {
            Variable relevantVar = null
            Value relevantVal = null
            if (!target.relevantVariables.get(value.variable)) {
                target.relevantVariables.put(value.variable, value.variable)
                relevantVar = value.variable
            } else {
                relevantVar = target.relevantVariables.get(value.variable)
            }

            if (!relevantVar.values.get(value)) {
                relevantVar.values.put(value, value)
                relevantVal = value
            } else {
                relevantVal = relevantVar.getValue(value)
            }
            relevantVal.incrementCountFor(targetVal)
        }
        this.data.add(new Tuple2<Value, Collection<Value>>(targetVal, row))
    }

    // XXX todo improve contract
    Table<Long, Variable, Value> getDataset() {
        throw new NotImplementedException()
    }
}
