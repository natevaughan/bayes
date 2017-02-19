package com.natevaughan.bayes.facade

import com.google.common.collect.HashBasedTable
import com.natevaughan.bayes.dataset.BaseDataset
import com.natevaughan.bayes.dataset.SparseDataset
import com.natevaughan.bayes.processor.BayesianCountProcessor
import com.natevaughan.bayes.processor.SelectAllVariablesProcessor
import com.natevaughan.bayes.processor.SimpleProcessingChain
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import sun.java2d.pipe.SpanShapeRenderer

/**
 * Created by nate on 2/18/17.
 */
class SparseBayesianPredictor implements Predictor {

    Target target
    SparseDataset dataset
    SimpleProcessingChain processingChain

    SparseBayesianPredictor(Target target) {
        this.target = target
        this.dataset = new SparseDataset(target)
        this.processingChain = new SimpleProcessingChain()
        this.processingChain.addProcessingStep(new SelectAllVariablesProcessor())
        this.processingChain.addProcessingStep(new BayesianCountProcessor())
    }


    void train(Map<Variable, Value> data) {
        dataset.train()
    }

    void train(Collection<Map<Variable, Value>> data) {

    }

    Value predict(Map<Variable, Value> data) {
        return null
    }

    void retrain() {

    }
}
