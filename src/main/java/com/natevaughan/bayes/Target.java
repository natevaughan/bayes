package com.natevaughan.bayes;

/**
 * Created by nate on 1/17/17.
 */
public class Target<T> {
    private final String name;
    private final T targetValue;
    private Variable targetVariable;

    public Target(String name, T targetValue) {
        this.name = name;
        this.targetValue = targetValue;
    }

    public String getName() {
        return name;
    }

    public T getTargetValue() {
        return targetValue;
    }

    public Variable getTargetVariable() {
        return targetVariable;
    }

    public void setTargetVariable(Variable targetVariable) {
        this.targetVariable = targetVariable;
    }
}
