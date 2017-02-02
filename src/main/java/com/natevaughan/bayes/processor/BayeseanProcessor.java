package com.natevaughan.bayes.processor;

import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.Dataset;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;

/**
 * Created by nate on 2/1/17.
 */
public class BayeseanProcessor implements DatasetProcessor {
    public Dataset process(Dataset dataset) {
        Table<Long, Variable, Value> table = dataset.getDataset();
        Target target = dataset.getTarget();

        for (Long rowId : table.rowKeySet()) {
            Value targetVal = table.get(rowId, dataset.getTarget());
            for (Variable var : table.columnKeySet()) {
                Value val = table.get(rowId, var);
                if (target.isPositive(targetVal)) {
                    val.incrementPositiveCountFor(targetVal);
                }
            }
        }
        return dataset;
    }

    public Dataset predict(Dataset dataset) {
        return null;
    }
}
