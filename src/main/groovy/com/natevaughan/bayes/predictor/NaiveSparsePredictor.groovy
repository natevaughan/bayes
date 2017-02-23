package com.natevaughan.bayes.predictor

import com.natevaughan.bayes.dataset.SparseDataset
import com.natevaughan.bayes.exception.BadRowException
import com.natevaughan.bayes.variable.PredictionValue
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
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

    Target target
    SparseDataset dataset

    NaiveSparsePredictor(Target target) {
        this.target = target
        this.dataset = new SparseDataset(target)
    }


    void train(Tuple2<Value, Collection<Value>> data) {
        dataset.train(data.first, data.second)
    }

    void trainAll(Collection<Tuple2<Value, Collection<Value>>> data) {
        for (Tuple2<Value, Collection<Value>> values : data) {
            train(values)
        }
    }

    PredictionValue predict(Collection<Value> data, boolean strict = false) {
        return target.predict(data)
    }

    void retrain() {

    }
}
