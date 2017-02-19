package com.natevaughan.bayes.facade

import com.google.common.collect.HashBasedTable
import com.natevaughan.bayes.dataset.BaseDataset
import com.natevaughan.bayes.processor.BayesianCountProcessor
import com.natevaughan.bayes.processor.ProcessingChain
import com.natevaughan.bayes.processor.SelectAllVariablesProcessor
import com.natevaughan.bayes.processor.SimpleProcessingChain

import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic

/**
 * Created by nate on 2/18/17.
 */
@CompileStatic
class BayesianRelationshipPredictor implements Predictor {

    private final Target target
    private final ProcessingChain processingChain
    private final BaseDataset dataset

    BayesianRelationshipPredictor(Target target) {
        this.target = target
        this.dataset = new BaseDataset(HashBasedTable.create())
        this.dataset.setTarget(target)
        this.processingChain = new SimpleProcessingChain()
        this.processingChain.addProcessingStep(new SelectAllVariablesProcessor())
        this.processingChain.addProcessingStep(new BayesianCountProcessor())
    }

    void train(Map<Variable, Value> data) {
        dataset.getDataset()
    }

    void train(Collection<Map<Variable, Value>> data) {

    }

    Value predict(Map<Variable, Value> data) {
        return null
    }

    void retrain() {

    }
}
