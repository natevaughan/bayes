package com.natevaughan.bayes.predictor

import com.natevaughan.bayes.dataset.SparseDataset
import com.natevaughan.bayes.processor.BayesianCountProcessor
import com.natevaughan.bayes.processor.SelectAllVariablesProcessor
import com.natevaughan.bayes.processor.SimpleProcessingChain
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable

/**
 * Created by nate on 2/18/17.
 *
 * A naive predictor that considers all evidence separately and thus does not
 * require a certain number of variables to make a prediction. Useful for spam
 * filtering or other text-based filtering
 */
class NaiveSparsePredictor implements Predictor {

    Target target
    SparseDataset dataset
    SimpleProcessingChain processingChain

    NaiveSparsePredictor(Target target) {
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
