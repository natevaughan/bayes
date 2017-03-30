package com.natevaughan.bayes.variable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by nate on 2/3/17.
 */
public class PredictionValue extends BooleanValue {

    private final BigDecimal likelihoodPositive;
    private final BigDecimal likelihoodNegative;
    private final Collection<ContributionRatio> contributions;

    public PredictionValue(BigDecimal likelihoodPositive, BigDecimal likelihoodNegative, Variable variable, Collection<ContributionRatio> contributions) {
        super((likelihoodPositive.compareTo(likelihoodNegative) > 0), variable);
        this.likelihoodPositive = likelihoodPositive;
        this.likelihoodNegative = likelihoodNegative;
        this.contributions = Collections.unmodifiableCollection(contributions);
    }

    @Override
    public String toString() {
        return name.toString() + "(" + (likelihoodPositive.divide(likelihoodPositive.add(likelihoodNegative), 10, RoundingMode.HALF_UP)) + ")";
    }

    public BigDecimal getLikelihoodPositive() {
        return likelihoodPositive;
    }

    public BigDecimal getLikelihoodNegative() {
        return likelihoodNegative;
    }

    public Collection<ContributionRatio> getContributions() {
        return contributions;
    }
}
