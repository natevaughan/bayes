package com.natevaughan.bayes.processor;

import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.AffinityStrategy;
import com.natevaughan.bayes.dataset.Dataset;

/**
 * Created by nate on 1/30/17.
 */
public interface DatasetPreProcessor {
    void setAffinityStrategy(AffinityStrategy strategy);
    Dataset preProcess(Table<Long, String, String> data);
}
