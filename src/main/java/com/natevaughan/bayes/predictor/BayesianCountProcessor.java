package com.natevaughan.bayes.predictor;

import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.Dataset;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;

/**
 * Created by nate on 2/1/17.
 */
public class BayesianCountProcessor implements DatasetProcessor {
    public Dataset process(Dataset dataset) {
        Table<Long, Variable, Value> table = dataset.getDataset();
        Target target = dataset.getTarget();

        for (Long rowId : table.rowKeySet()) {
            Value targetVal = table.get(rowId, target.getTargetVariable());
            if (target.isPositive(targetVal)) {
                target.incrementPositiveCount();
            } else {
                target.incrementNegativeCount();
            }

            for (Variable var : target.getRelevantVariables().keySet()) {
                Value val = table.get(rowId, var);
                if (var.getValues().get(val) == null) {
                    var.getValues().put(val, val);
                } else {
                    val = var.getValues().get(val);
                }
                val.incrementCountFor(targetVal);
                table.put(rowId, var, val);
            }
        }
        return dataset;
    }
}
