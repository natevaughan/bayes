package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.dataset.Dataset;
import com.natevaughan.bayes.variable.Variable;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Nate Vaughan
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
