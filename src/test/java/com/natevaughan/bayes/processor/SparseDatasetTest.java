package com.natevaughan.bayes.processor;

import com.natevaughan.bayes.predictor.NaiveSparsePredictor;
import com.natevaughan.bayes.variable.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nate on 2/13/17.
 */
public class SparseDatasetTest {

    private CategoricalVariable targetVar = new CategoricalVariable("TARGET");
    private Value trueVal = new BooleanValue(true, targetVar);

    Collection<Value> vals = Arrays.asList(new Value[] {
            new BooleanValue(true, new CategoricalVariable("nate"))
    });

    // XXX todo port to Spock
    @Ignore // WIP
    @Test
    public void simpleSparsePredictorTest() {
        NaiveSparsePredictor predictor = createSparsePredictor();
        predictor.train(createTrainingRow());
        Value prediction = predictor.predict(createTrainingRow());
        assertNotNull(prediction);
        assertTrue(prediction.getVariable().equals(targetVar));
        assertTrue(prediction.getName().equals("bar"));
    }

    private Map<Variable, Value> createTrainingRow() {
        Map<Variable, Value> row = new HashMap<>();
        row.put(targetVar, trueVal);
        CategoricalVariable fooVar = new CategoricalVariable("foo");
        row.put(fooVar, new CategoricalValue("bar", fooVar));
        return row;
    }

    private NaiveSparsePredictor createSparsePredictor() {
        Target t = new BinaryTarget(targetVar, trueVal);
        NaiveSparsePredictor predictor = new NaiveSparsePredictor(t);
        return predictor;
    }
}
