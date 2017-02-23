package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.dataset.Dataset;

import java.util.List;

/**
 * Created by nate on 2/1/17.
 */
public interface ProcessingChain {
    List<DatasetProcessor> getProcessingSteps();
    void addProcessingStep(DatasetProcessor processor);
    void processAll(Dataset dataset);
}
