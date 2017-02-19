package com.natevaughan.bayes.facade


import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic

/**
 * Created by nate on 2/18/17.
 */
@CompileStatic
interface Predictor {
    void train(Map<Variable, Value>  data)
    void train(Collection<Map<Variable, Value>>  data)
    Value predict(Map<Variable, Value> data)
    void retrain()
}
