package com.natevaughan.bayes.processor;

import com.natevaughan.bayes.dataset.SparseDataset;
import com.natevaughan.bayes.variable.BinaryTarget;
import com.natevaughan.bayes.variable.BooleanValue;
import com.natevaughan.bayes.variable.CategoricalVariable;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by nate on 2/13/17.
 */
public class SparseDatasetTest {

    Collection<Value> vals = Arrays.asList(new Value[] {
            new BooleanValue(true, new CategoricalVariable("nate"))
    });

    // XXX todo port to Spock
    @Test
    public void sparseDatasetTest() {
        SparseDataset dataset = createSparseDataset();
        dataset.train(new BooleanValue(true, new CategoricalVariable("TARGET")), vals);
    }

    private SparseDataset createSparseDataset() {
        CategoricalVariable var = new CategoricalVariable("TARGET");
        Value trueVal = new BooleanValue(true, var);
        Target t = new BinaryTarget(var, trueVal);
        SparseDataset dataset = new SparseDataset(t);
        return dataset;
    }
}
