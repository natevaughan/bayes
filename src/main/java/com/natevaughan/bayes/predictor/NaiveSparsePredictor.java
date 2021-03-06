package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.variable.PredictionValue;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;
import org.apache.commons.lang3.tuple.Pair;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Nate Vaughan
 */
public class NaiveSparsePredictor implements Predictor {

	private final String name;
	private final Target target;
	private final List<Pair<Value, Collection<Value>>> data = new ArrayList<>();

	NaiveSparsePredictor(String name, Target target) {
		this.name = name;
		this.target = target;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void train(Pair<Value, Collection<Value>> data) {
		try {
			train(data.getLeft(), data.getRight());
		} catch (ParseException e) {
		}
	}

	@Override
	public void trainAll(Collection<Pair<Value, Collection<Value>>> data) {

		for (Pair<Value, Collection<Value>> values : data) {
			train(values);
		}
	}

	@Override
	public PredictionValue predict(Collection<Value> data) {
		return predict(data, false);
	}

	public PredictionValue predict(Collection<Value> data, boolean strict) {
		return target.predict(data);
	}

	public void train(Value targetVal, Collection<Value> row) throws ParseException {

		if (targetVal == null || targetVal.getVariable() != target.getTargetVariable()) {
			throw new ParseException("Cannot train on row with mismatched target", 0);
		}

		if (target.isPositive(targetVal)) {
			target.incrementPositiveCount();
		} else {
			target.incrementNegativeCount();
		}

		for (Value value : row) {
			Variable relevantVar = null;
			Value relevantVal = null;
			if (target.getRelevantVariables().get(value.getVariable()) == null) {
				target.getRelevantVariables().put(value.getVariable(), value.getVariable());
				relevantVar = value.getVariable();
			} else {
				relevantVar = target.getRelevantVariables().get(value.getVariable());
			}
			if (relevantVar.getValues().get(value) == null) {
				relevantVar.getValues().put(value, value);
				relevantVal = value;
			} else {
				relevantVal = relevantVar.getValue(value);
			}
			relevantVal.incrementCountFor(targetVal);
		}

		this.data.add(Pair.of(targetVal, row));
	}

	@Override
	public void retrain() {
	}
}