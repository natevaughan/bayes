package com.natevaughan.bayes.processor;

import com.natevaughan.bayes.dataset.AffinityStrategy;
import com.natevaughan.bayes.dataset.Dataset;

/**
 * Created by nate on 1/30/17.
 */
public class AffinityProcessor implements DatasetProcessor {

    AffinityStrategy affinityStrategy;

    public AffinityProcessor(AffinityStrategy strategy){
        this.affinityStrategy = strategy;
    }

    public AffinityStrategy getAffinityStrategy() {
        return affinityStrategy;
    }

    public void setAffinityStrategy(AffinityStrategy affinityStrategy) {
        this.affinityStrategy = affinityStrategy;
    }

    public Dataset process(Dataset dataset) {
        return null;
    }

}
