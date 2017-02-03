package com.natevaughan.bayes.variable;

/**
 * Created by nate on 1/30/17.
 */
public class CategoricalValue extends AbstractValue {

    public final String name;
    public final CategoricalVariable variable;

    public CategoricalValue(String name, CategoricalVariable variable) {
        this.name = name;
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoricalValue that = (CategoricalValue) o;

        if (!name.equals(that.name)) return false;
        return variable.equals(that.variable);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + variable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
