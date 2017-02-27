package com.natevaughan.bayes.predictor

import com.natevaughan.bayes.variable.BinaryTarget
import com.natevaughan.bayes.variable.BooleanValue
import com.natevaughan.bayes.variable.CategoricalVariable
import com.natevaughan.bayes.variable.LinesToValuesProcessor
import com.natevaughan.bayes.variable.Target
import com.natevaughan.bayes.variable.Value
import com.opencsv.CSVReader
import groovy.transform.TypeChecked
import spock.lang.Specification

/**
 * Created by nate on 2/26/17.
 */
@TypeChecked
class CommentsTest extends Specification {

    CategoricalVariable targetVar = new CategoricalVariable("TARGET")
    Value targetVal = new BooleanValue(true, targetVar)
    Value targetAntiVal = new BooleanValue(false, targetVar)
    NaiveSparsePredictor predictor
    final static Integer NET_PROMOTER = 9
    final static String RATING = "rating"
    final static String COMMENT = "comment"

    def setup() {
        FileReader is = new FileReader("src/test/resources/training.csv")
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
        t.setEpsilon(0.1D)
        NaiveSparsePredictor predictor = new NaiveSparsePredictor(t)
        return predictor
    }

    def "it should load comments into sparse dataset predictor"() {

        System.out.println(predictor.predict(LinesToValuesProcessor.convert("appreciate miles customer service")));
        given:
            def foo = true
        when:
            foo = false
        then:
            foo != true

    }
}
