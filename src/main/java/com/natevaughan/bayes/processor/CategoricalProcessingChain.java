package com.natevaughan.bayes.processor;

import com.natevaughan.bayes.dataset.Dataset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nate on 2/1/17.
 */
public class CategoricalProcessingChain implements ProcessingChain {

    private List<DatasetProcessor> processingSteps = new ArrayList<>();

    public List<DatasetProcessor> getProcessingSteps() {
        return processingSteps;
    }

    public void addProcessingStep(DatasetProcessor processor) {
        processingSteps.add(processor);
    }

    public void processAll(Dataset dataset) {
        Dataset next = dataset;
        for (DatasetProcessor processor : this.processingSteps) {
            next = processor.process(next);
        }
    }

    public Dataset predictAll(Dataset dataset) {
        Dataset next = dataset;
        for (DatasetProcessor processor : this.processingSteps) {
            next = processor.predict(next);
        }
        return next;
    }
}
