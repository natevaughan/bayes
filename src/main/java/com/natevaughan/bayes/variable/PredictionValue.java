package com.natevaughan.bayes.variable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by nate on 2/3/17.
 */
public class PredictionValue extends BooleanValue {

    private BigDecimal likelihoodPositive;
    private BigDecimal likelihoodNegative;

    public PredictionValue(BigDecimal likelihoodPositive, BigDecimal likelihoodNegative, Variable variable) {
        super((likelihoodPositive.compareTo(likelihoodNegative) > 0), variable);
        this.likelihoodPositive = likelihoodPositive;
        this.likelihoodNegative = likelihoodNegative;
    }

    @Override
    public String toString() {
        return name.toString() + "(" + (likelihoodPositive.divide(likelihoodPositive.add(likelihoodNegative), 10, RoundingMode.HALF_UP)) + ")";
    }
}
