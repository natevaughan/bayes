package com.natevaughan.bayes.variable;

/**
 * Created by nate on 2/3/17.
 */
public class BooleanValue extends AbstractValue {

    public final Boolean name;
    public final Variable variable;

    public BooleanValue(Boolean name, Variable variable) {
        this.name = name;
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    public String getName() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanValue that = (BooleanValue) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return variable != null ? variable.equals(that.variable) : that.variable == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (variable != null ? variable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
