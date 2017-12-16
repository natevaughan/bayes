package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.variable.Value;
import groovy.lang.Tuple2;

import java.util.Collection;

/**
 * @author Nate Vaughan
 * 
 * Facade for abstracting the complexity of setting up different kinds of predictors
 */
public interface Predictor {
    String getName();
    void train(Tuple2<Value, Collection<Value>> data);
    void trainAll(Collection<Tuple2<Value, Collection<Value>>> data);
    Value predict(Collection<Value> data);
    void retrain();
}
