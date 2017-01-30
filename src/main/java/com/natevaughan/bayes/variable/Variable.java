package com.natevaughan.bayes.variable;

import com.natevaughan.bayes.Tuple2;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Variable {
    String getName();
    Collection<Value> getValues();
    Double getAffinity(Variable variable);
}
