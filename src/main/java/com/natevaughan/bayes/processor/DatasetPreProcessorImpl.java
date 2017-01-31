package com.natevaughan.bayes.processor;

import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.AffinityStrategy;
import com.natevaughan.bayes.dataset.Dataset;

/**
 * Created by nate on 1/30/17.
 */
public class DatasetPreProcessorImpl implements DatasetPreProcessor {

    AffinityStrategy affinityStrategy;

    public DatasetPreProcessorImpl(AffinityStrategy strategy){
        this.affinityStrategy = strategy;
    }

    public AffinityStrategy getAffinityStrategy() {
        return affinityStrategy;
    }

    public void setAffinityStrategy(AffinityStrategy affinityStrategy) {
        this.affinityStrategy = affinityStrategy;
    }

    public Dataset preProcess(Table<Long, String, String> data) {
        return null;
    }
}
