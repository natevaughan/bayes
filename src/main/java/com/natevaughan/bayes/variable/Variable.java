package com.natevaughan.bayes.variable;

import com.natevaughan.bayes.Tuple2;

import java.util.Collection;

/**
 * Created by nate on 1/30/17.
 */
public interface Variable {
    public String getName();
    public Collection<Value> getValues();
    public Double getAffinity(Variable variable);
}
