package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.dataset.Dataset;

/**
 * @author Nate Vaughan
 */
public interface DatasetProcessor {
    Dataset process(Dataset dataset);
}
