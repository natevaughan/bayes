package com.natevaughan.bayes.cli

import com.google.common.collect.Table
import com.natevaughan.bayes.dataset.BaseDataset
import com.natevaughan.bayes.dataset.Dataset
import com.natevaughan.bayes.processor.BayeseanCountProcessor
import com.natevaughan.bayes.processor.SelectAllVariablesProcessor
import com.natevaughan.bayes.processor.SimpleProcessingChain
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic

@CompileStatic
class BayesCLI {

    static void main(String[] args) {
        Reader reader = System.in.newReader()
        println 'what would you like to do?'
        String action = ''
        while (!Actions.QUIT.is(action)) {
            action = reader.readLine()
            if (Actions.NEW.is(action)) {
                println 'creating new target'
            }
            if (Actions.OPTIMIZE.is(action)) {
                println 'reprocessing target'
            }
            if (Actions.PREDICT.is(action)) {
                println 'ok, let\'s make some predictions'
            }
            if (Actions.TRAIN.is(action)) {
                println 'ready to train target'
            }
            if (Actions.TARGETS.is(action)) {
                println 'here are your available targets'
            }
        }
        println 'bye'
    }

    Dataset process(Table<Long, Variable, Value> data) {
        SimpleProcessingChain chain = new SimpleProcessingChain()
        chain.addProcessingStep(new SelectAllVariablesProcessor())
        chain.addProcessingStep(new BayeseanCountProcessor())
        Dataset dataset = new BaseDataset(data)
        chain.processAll(dataset)
        dataset.getTarget().setEpsilon(0.1D)
        return dataset;
    }
}
