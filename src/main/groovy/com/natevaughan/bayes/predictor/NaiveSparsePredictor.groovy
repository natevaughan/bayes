package com.natevaughan.bayes.predictor

import com.natevaughan.bayes.exception.BadRowException
import com.natevaughan.bayes.variable.PredictionValue
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic

/**
 * Created by nate on 2/18/17.
 *
 * A naive predictor that considers all evidence separately and thus does not
 * require a certain number of variables to make a prediction. Useful for spam
 * filtering or other text-based filtering
 */
@CompileStatic
class NaiveSparsePredictor implements Predictor {

    String name
    Target target
    private final List<Tuple2<Value, Collection<Value>>> data = []

    NaiveSparsePredictor(Target target) {
        this.target = target
    }


    void train(Tuple2<Value, Collection<Value>> data) {
        train(data.first, data.second)
    }

    void trainAll(Collection<Tuple2<Value, Collection<Value>>> data) {
        for (Tuple2<Value, Collection<Value>> values : data) {
            train(values)
        }
    }

    PredictionValue predict(Collection<Value> data, boolean strict = false) {
        return target.predict(data)
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

    void retrain() {

    }

}
