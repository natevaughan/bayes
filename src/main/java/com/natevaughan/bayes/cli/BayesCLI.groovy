package com.natevaughan.bayes.cli

import com.google.common.collect.Table
import com.natevaughan.bayes.dataset.BaseDataset
import com.natevaughan.bayes.dataset.Dataset
import com.natevaughan.bayes.predictor.BayesianCountProcessor
import com.natevaughan.bayes.predictor.NaiveSparsePredictor
import com.natevaughan.bayes.predictor.java
import com.natevaughan.bayes.predictor.SelectAllVariablesProcessor
import com.natevaughan.bayes.predictor.SimpleProcessingChain
import com.natevaughan.bayes.variable.BinaryTarget
import com.natevaughan.bayes.variable.CategoricalValue
import com.natevaughan.bayes.variable.CategoricalVariable
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.natevaughan.bayes.variable.Variable
import groovy.transform.CompileStatic

@CompileStatic
class BayesCLI {

    static List<java> predictors = new ArrayList<>()
    static java currentPredictor
    static Reader reader = System.in.newReader()

    static void main(String[] args) {
        println 'what would you like to do?'
        String action = ''
        while (!Actions.QUIT.is(action)) {
            action = getLine()
            if (Actions.NEW.is(action)) {
                println 'give your dataset a name:'
                String name = getLine()
                println 'enter the column name of your target variable:'
                String targetName = getLine()
                println 'enter the value you would like to target:'
                String targetValName = getLine()
                CategoricalVariable targetVar = new CategoricalVariable(targetName)
                Value targetVal = new CategoricalValue(targetValName, targetVar)
                Target t = new BinaryTarget(targetVar, targetVal)
                t.setEpsilon(5.0D)
                NaiveSparsePredictor predictor = new NaiveSparsePredictor(t)
                predictor.name = name
                predictors.add(predictor)
                currentPredictor = predictor
            }
            if (Actions.OPTIMIZE.is(action)) {
                println 'reprocessing target'
            }
            if (Actions.PREDICT.is(action)) {
                println 'ok, let\'s make some predictions'
            }
            if (Actions.TRAIN.is(action)) {
                println 'please specify a file path:\n'
                String path = getLine()
                File file = new File(path)
                println 'file exists: ' + file.exists()
                println 'file is file: ' + file.isFile()
                println 'first line: ' + file.newReader().readLine()
            }
            if (Actions.LIST.is(action)) {
                println 'here are your available targets'
                for (java ds : predictors) {
                    println ds.name
                }
            }
        }
        println 'bye'
    }

    static String getLine() {
        String line = reader.readLine()
        if (line == null || line == "") {
            println "error, please try again"
            line = reader.readLine()
        }
        return line
    }

    Dataset process(Table<Long, Variable, Value> data) {
        SimpleProcessingChain chain = new SimpleProcessingChain()
        chain.addProcessingStep(new SelectAllVariablesProcessor())
        chain.addProcessingStep(new BayesianCountProcessor())
        Dataset dataset = new BaseDataset(data)
        chain.processAll(dataset)
        dataset.getTarget().setEpsilon(0.1D)
        return dataset;
    }
}
