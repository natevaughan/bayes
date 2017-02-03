package com.natevaughan.bayes.processor;

import com.natevaughan.bayes.dataset.Dataset;
import com.natevaughan.bayes.variable.Variable;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nate on 2/3/17.
 */
public class SelectAllVariablesProcessor implements DatasetProcessor {

    public Dataset process(Dataset dataset) {
        Collection<Variable> vars = dataset.getDataset().columnKeySet();
        Collection<Variable> nonTargetVars = new HashSet<>();
        for (Variable var : vars) {
            if(!var.equals(dataset.getTarget().getTargetVariable())) {
                nonTargetVars.add(var);
            }
        }
        dataset.getTarget().setRelevantVariables(nonTargetVars);
        return dataset;
    }
}
