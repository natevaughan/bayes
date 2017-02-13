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
    private final List<Variable> variables = new ArrayList()

    SparseDataset(Target target) {
        this.target = target
    }

    @Override
    Table<Long, Variable, Value> getDataset() {
        throw new NotImplementedException()
    }

    Target getTarget() {
        target
    }

    Collection<Variable> getVariables() {
        variables
    }

    void train(Tuple2<Variable, Value> target, Collection<Tuple2<Variable, Value>> otherValues) {

    }
}
