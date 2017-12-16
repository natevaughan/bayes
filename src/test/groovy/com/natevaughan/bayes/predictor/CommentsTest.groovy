package com.natevaughan.bayes.predictor

import com.natevaughan.bayes.variable.BinaryTarget
import com.natevaughan.bayes.variable.BooleanValue
import com.natevaughan.bayes.variable.CategoricalVariable
import com.natevaughan.bayes.variable.ContributionRatio
import com.natevaughan.bayes.variable.LinesToValuesProcessor
import com.natevaughan.bayes.variable.PredictionValue
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.opencsv.CSVReader
import spock.lang.Specification

/**
 * Author Nate Vaughan
 */
class CommentsTest extends Specification {

    CategoricalVariable targetVar = new CategoricalVariable("TARGET")
    Value targetVal = new BooleanValue(true, targetVar)
    Value targetAntiVal = new BooleanValue(false, targetVar)
    NaiveSparsePredictor predictor
    final static Integer NET_PROMOTER = 9
    final static String RATING = "rating"
    final static String COMMENT = "comment"
    final static Double EPSILON = 0.01D

    def "it should rank contributors"() {
        given:
        String comment = "good great awesome pillows"

        when:

        Value predictionValue = predictor.predict(LinesToValuesProcessor.convert(comment))
        println predictionValue
        println "-----------------"


        then:
        predictionValue != null
        for (ContributionRatio ratio : predictionValue.contributions) {
            ratio != null
            println ratio
        }
    }





    def "it should validate a training set"() {

        FileReader is = new FileReader("src/test/resources/ratings_validate.csv")
        CSVReader reader = new CSVReader(is)
        String[] headerKeys = reader.readNext()
        Integer ratingIndex
        Integer commentIndex

        for (int i in 0..< headerKeys.length) {
            if (headerKeys[i].equalsIgnoreCase(RATING)) {
                ratingIndex = i
            }
            if (headerKeys[i].equalsIgnoreCase(COMMENT)) {
                commentIndex = i
            }
        }


        Long truePositive = 0
        Long falsePositive = 0
        Long falseNegative = 0
        Long trueNegative = 0
        Long weak = 0

        String[] line = reader.readNext()
        while (line != null) {
            try {
                PredictionValue result = predictor.predict(LinesToValuesProcessor.stripNonAlphaAndConvert(line[commentIndex]))
                Integer rating = Integer.parseInt(line[ratingIndex])

                // experimental code to see if excluding weak predictions improves performance
//                BigDecimal confidence = result.likelihoodPositive / (result.likelihoodPositive + result.likelihoodNegative)
//                if ((confidence.doubleValue() - 0.5) < 0.05 && (confidence.doubleValue() - 0.5) > -0.05 ) {
//                    ++weak
//                    throw new Exception("skip")
//                }

                if (rating >= NET_PROMOTER) {
                    if (result.name == 'true') {
                        ++truePositive
                    } else {
                        ++falsePositive
                    }
                } else {
                    if (result.name == 'true') {
                        ++falseNegative
                    } else {
                        ++trueNegative
                    }
                }
            } catch (Exception e) {
                println "caught exception"
            }
            line = reader.readNext()
        }

        Long sum = (truePositive + falsePositive + falsePositive + falseNegative)


        println "Accuracy: " + (truePositive + falsePositive) / (sum)
        println "TP: $truePositive | FP: $falsePositive"
        println "FN: $falseNegative | TN: $trueNegative"
//        println "weak (follow up): $weak"

        given:
        def foo = true
        when:
        foo = false
        then:
        foo != true
    }


    def setup() {
        FileReader is = new FileReader("src/test/resources/training.csv.sample")
        CSVReader reader = new CSVReader(is)
        String[] headerKeys = reader.readNext()
        Integer ratingIndex
        Integer commentIndex
        for (int i in 0..< headerKeys.length) {
            if (headerKeys[i].equalsIgnoreCase(RATING)) {
                ratingIndex = i
            }
            if (headerKeys[i].equalsIgnoreCase(COMMENT)) {
                commentIndex = i
            }
        }
        String[] line = reader.readNext()
        predictor = createSparsePredictor()
        while (line != null) {
            try {
                Integer rating = Integer.parseInt(line[ratingIndex])
                if (rating >= NET_PROMOTER) {
                    predictor.train(new Tuple2<Value, Collection<Value>>(targetVal, LinesToValuesProcessor.stripNonAlphaAndConvert(line[commentIndex])))
                } else {
                    predictor.train(new Tuple2<Value, Collection<Value>>(targetAntiVal, LinesToValuesProcessor.stripNonAlphaAndConvert(line[commentIndex])))
                }
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
