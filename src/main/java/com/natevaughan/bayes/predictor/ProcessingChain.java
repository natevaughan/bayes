package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.dataset.Dataset;

import java.util.List;

/**
 * @author Nate Vaughan
 */
public interface ProcessingChain {
    List<DatasetProcessor> getProcessingSteps();
    void addProcessingStep(DatasetProcessor processor);
    void processAll(Dataset dataset);
}
