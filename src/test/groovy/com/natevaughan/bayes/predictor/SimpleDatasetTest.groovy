package com.natevaughan.bayes.predictor

import com.natevaughan.bayes.variable.BinaryTarget
import com.natevaughan.bayes.variable.BooleanValue
import com.natevaughan.bayes.variable.CategoricalValue
import com.natevaughan.bayes.variable.CategoricalVariable
import com.natevaughan.bayes.variable.ContributionRatio
import com.natevaughan.bayes.variable.PredictionValue
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.opencsv.CSVReader
import spock.lang.Shared
import spock.lang.Specification

/**
 * Author Nate Vaughan
 */
class SimpleDatasetTest extends Specification {

    final static String TARGET = "disease"
    final static String TARGET_VALUE = "yes"
    CategoricalVariable targetVar = new CategoricalVariable(TARGET)
    Value targetVal = new BooleanValue(true, targetVar)
    Value targetAntiVal = new BooleanValue(false, targetVar)
    NaiveSparsePredictor predictor
    final static Double EPSILON = 0.01D

    @Shared CategoricalValue test1Positive = new CategoricalValue("positive", new CategoricalVariable("test1"))
    @Shared CategoricalValue test2Positive = new CategoricalValue("positive", new CategoricalVariable("test2"))
    @Shared CategoricalValue test1Neg = new CategoricalValue("neg", new CategoricalVariable("test1"))
    @Shared CategoricalValue test2Neg = new CategoricalValue("neg", new CategoricalVariable("test2"))

    def "it should predict on some stuff"(String prediction, List<Value> vals) {
        given:
        PredictionValue predictionValue = predictor.predict(vals)
        println predictionValue
        println "-----------------"
        for (ContributionRatio ratio : predictionValue.contributions) {
            println ratio
        }
        println "^^^^^^^^^^^^^^^^^\n\n"

        expect:
        prediction == predictionValue.name

        where:
        prediction   | vals
        'true'       | [test1Positive, test2Positive]
        'false'      | [test1Neg, test2Positive]
        'false'      | [test1Positive, test2Neg]
        'false'      | [test1Neg, test2Neg]

    }

    def setup() {
        FileReader is = new FileReader("src/test/resources/simple.csv.sample")
        CSVReader reader = new CSVReader(is)
        String[] headerKeys = reader.readNext()
        Integer ratingIndex
        Map<Integer, CategoricalVariable> categoricalVariableList = [:]
        for (int i in 0..< headerKeys.length) {
            if (headerKeys[i].equalsIgnoreCase(TARGET)) {
                ratingIndex = i
            }
            categoricalVariableList.put(i, new CategoricalVariable(headerKeys[i]))
        }
        String[] line = reader.readNext()
        predictor = createSparsePredictor()
        Value rowTargetValue
        List<Value> rowOtherValues
        while (line != null) {
            try {
                rowOtherValues = []
                for (int i in 0..< headerKeys.length) {
                    if (ratingIndex == i) {
                        if (line[i] == TARGET_VALUE) {
                            rowTargetValue = targetVal
                        } else {
                            rowTargetValue = targetAntiVal
                        }
                    } else {
                        rowOtherValues << new CategoricalValue(line[i], categoricalVariableList.get(i))
                    }
                }
                predictor.train(new Tuple2<Value, Collection<Value>>(rowTargetValue, rowOtherValues))
            } catch (NumberFormatException | NullPointerException e) {
                println 'Bad number : ' +line[ratingIndex]
            }
            line = reader.readNext()
        }

    }

    private NaiveSparsePredictor createSparsePredictor() {
        Target t = new BinaryTarget(targetVar, targetVal)
        t.setEpsilon(EPSILON)
        NaiveSparsePredictor predictor = new NaiveSparsePredictor("foo", t)
        return predictor
    }

}
