package com.natevaughan.bayes.processor;

import com.natevaughan.bayes.dataset.Dataset;

/**
 * Created by nate on 2/1/17.
 */
public interface DatasetProcessor {
    Dataset process(Dataset dataset);
    Dataset predict(Dataset dataset);
}
