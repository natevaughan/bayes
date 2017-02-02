package com.natevaughan.bayes.dataset;

import com.google.common.collect.Table;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;

import java.util.Collection;

/**
 * Created by nate on 2/1/17.
 */
public class BaseDataset implements Dataset {

    private final Table<Long, Variable, Value> dataset;
    private Target target;

    public BaseDataset(Table<Long, Variable, Value> dataset) {
        this.dataset = dataset;
    }

    public Table<Long, Variable, Value> getDataset() {
        return dataset;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public Collection<Variable> getVariables() {
        return dataset.columnKeySet();
    }
}
