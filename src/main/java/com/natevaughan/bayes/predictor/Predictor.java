package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.variable.Value;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

/**
 * @author Nate Vaughan
 * 
 * Facade for abstracting the complexity of setting up different kinds of predictors
 */
public interface Predictor {
    String getName();
    void train(Pair<Value, Collection<Value>> data);
    void trainAll(Collection<Pair<Value, Collection<Value>>> data);
    Value predict(Collection<Value> data);
    void retrain();
}
