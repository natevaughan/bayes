package com.natevaughan.bayes.variable;

/**
 * Created by nate on 2/3/17.
 */
public class PredictionValue extends BooleanValue {

    private Double likelihoodPositive;
    private Double likelihoodNegative;

    public Double getLikelihoodPositive() {
        return likelihoodPositive;
    }

    public PredictionValue(Double likelihoodPositive, Double likelihoodNegative, Variable variable) {
        super((likelihoodPositive > likelihoodNegative), variable);
        this.likelihoodPositive = likelihoodPositive;
        this.likelihoodNegative = likelihoodNegative;
    }

    public void setLikelihoodPositive(Double likelihoodPositive) {
        this.likelihoodPositive = likelihoodPositive;
    }

    public Double getLikelihoodNegative() {
        return likelihoodNegative;
    }

    public void setLikelihoodNegative(Double likelihoodNegative) {
        this.likelihoodNegative = likelihoodNegative;
    }

    @Override
    public String toString() {
        return name.toString() + "(" + Double.toString(likelihoodPositive / (likelihoodPositive + likelihoodNegative)).substring(0, 5) + ")";
    }
}
