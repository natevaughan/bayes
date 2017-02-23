package com.natevaughan.bayes.predictor


import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic

/**
 * Created by nate on 2/18/17.
 * 
 * Facade for abstracting the complexity of setting up different kinds of predictors
 */
@CompileStatic
interface Predictor {
    void train(Tuple2<Value, Collection<Value>> data)
    void trainAll(Collection<Tuple2<Value, Collection<Value>>> data)
    Value predict(Collection<Value> data)
    void retrain()
}
