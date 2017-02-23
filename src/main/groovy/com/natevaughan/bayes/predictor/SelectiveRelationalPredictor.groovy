package com.natevaughan.bayes.predictor

import com.google.common.collect.HashBasedTable
import com.natevaughan.bayes.dataset.BaseDataset
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import groovy.transform.CompileStatic

/**
 * Created by nate on 2/18/17.
 *
 * Facade for predictor that scans a dataset with n processing steps
 */
@CompileStatic
class SelectiveRelationalPredictor implements Predictor {

    private final Target target
    private final ProcessingChain processingChain
    private final BaseDataset dataset

    SelectiveRelationalPredictor(Target target) {
        this.target = target
        this.dataset = new BaseDataset(HashBasedTable.create())
        this.dataset.setTarget(target)
        this.processingChain = new SimpleProcessingChain()
        this.processingChain.addProcessingStep(new SelectAllVariablesProcessor())
        this.processingChain.addProcessingStep(new BayesianCountProcessor())
    }

    void train(Tuple2<Value, Collection<Value>> data) {
        dataset.getDataset()
    }

    void trainAll(Collection<Tuple2<Value, Collection<Value>>> data) {

    }

    Value predict(Collection<Value> data) {
        return null
    }

    void retrain() {

    }
}
