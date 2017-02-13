package com.natevaughan.bayes.dataset

import com.google.common.collect.Table
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
    private final Map<Variable, Variable> variables = new HashMap()

    SparseDataset(Target target) {
        this.target = target
    }

    Target getTarget() {
        target
    }

    Collection<Variable> getVariables() {
        variables.keySet()
    }

    void train(Value targetVal, Collection<Value> otherValues) {
        if (target.isPositive(targetVal)) {
            target.incrementPositiveCount()
        } else {
            target.incrementNegativeCount()
        }

        for (Value value : otherValues) {
            Variable relevantVar = null
            Value relevantVal = null
            if (!variables.get(value.variable)) {
                variables.put(value.variable, value.variable)
                relevantVar = value.variable
            } else {
                relevantVar = variables.get(value.variable)
            }
            if (!relevantVar.values.get(value)) {
                relevantVar.values.put(value, value)
                relevantVal = value
            } else {
                relevantVal = relevantVar.getValue(value)
            }
            relevantVal.incrementCountFor(targetVal)
        }
    }

    // XXX todo improve contract
    Table<Long, Variable, Value> getDataset() {
        throw new NotImplementedException()
    }
}
