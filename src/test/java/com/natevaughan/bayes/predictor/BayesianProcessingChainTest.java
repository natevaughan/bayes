package com.natevaughan.bayes.predictor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.BaseDataset;
import com.natevaughan.bayes.dataset.Dataset;
import com.natevaughan.bayes.variable.BinaryTarget;
import com.natevaughan.bayes.variable.CategoricalValue;
import com.natevaughan.bayes.variable.CategoricalVariable;
import com.natevaughan.bayes.variable.PredictionValue;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertFalse;

/**
 * @author Nate Vaughan
 */
public class BayesianProcessingChainTest {

    Logger log = LoggerFactory.getLogger(getClass());

    private final String TARGET_COLUMN_HEADER = "TARGET";
    private final String TARGET_VALUE = "yes";
    private final Integer TARGET_HEADER_INDEX = 4;
    private final String[] HEADER = new String[]{"outlook","temp","humidity","windy", TARGET_COLUMN_HEADER};
    private final String[] CLASSES = new String[]{"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String"};
    private final String[][] ROWS = new String[][] {
        new String[]{"rainy","hot","high","false","no"},
        new String[]{"rainy","hot","high","true","no"},
        new String[]{"overcast","hot","high","false","yes"},
        new String[]{"sunny","mild","high","false","yes"},
        new String[]{"sunny","cool","normal","false","yes"},
        new String[]{"sunny","cool","normal","true","no"},
        new String[]{"overcast","cool","normal","true","yes"},
        new String[]{"rainy","mild","high","false","no"},
        new String[]{"rainy","cool","normal","false","yes"},
        new String[]{"sunny","mild","normal","false","yes"},
        new String[]{"rainy","mild","normal","true","yes"},
        new String[]{"overcast","mild","high","true","yes"},
        new String[]{"overcast","hot","normal","false","yes"},
        new String[]{"sunny","mild","high","true","no"}
    };

    @Test
    public void testGolfData()  {
        SimpleProcessingChain chain = new SimpleProcessingChain();
        chain.addProcessingStep(new SelectAllVariablesProcessor());
        chain.addProcessingStep(new BayesianCountProcessor());
        Dataset dataset = createMockDataset();
        chain.processAll(dataset);
        Target target = dataset.getTarget();
        target.setEpsilon(0.0);
        PredictionValue predictions = target.predict(createSinglePrediction());
        log.debug(predictions.toString());
    }

    private Collection<Value> createSinglePrediction() {
        Map<Integer, CategoricalVariable> variableMap = createHeaderMap();
        Collection<Value> data = new HashSet<>();
        data.add(new CategoricalValue("sunny", variableMap.get(0)));
        data.add(new CategoricalValue("cool", variableMap.get(1)));
        data.add(new CategoricalValue("normal", variableMap.get(2)));
        data.add(new CategoricalValue("false", variableMap.get(3)));
        return data;
    }

    private Dataset createMockPredictionDataset() {
        Map<Integer, CategoricalVariable> variableMap = createHeaderMap();

        Table<Long, Variable, Value> data = HashBasedTable.create();
        for (int i = 0; i < ROWS.length; ++i) {
            for (int j = 0; j < HEADER.length; ++j) {
                if (j == TARGET_HEADER_INDEX) {
                    continue;
                }
                data.put((long) i, variableMap.get(j), new CategoricalValue(ROWS[i][j], variableMap.get(j)));
            }
        }
        BaseDataset ds = new BaseDataset(data);
        return ds;
    }

    private Map<Integer, CategoricalVariable> createHeaderMap() {
        Map<Integer, CategoricalVariable> variableMap = new HashMap<>();
        for (int i = 0; i < HEADER.length; ++i) {
            if (i == TARGET_HEADER_INDEX) {
                continue;
            }
            variableMap.put(i, new CategoricalVariable(HEADER[i]));
        }
        return variableMap;
    }

    private Dataset createMockDataset() {
        Map<Integer, CategoricalVariable> variableMap = new HashMap<>();
        for (int i = 0; i < HEADER.length; ++i) {
            variableMap.put(i, new CategoricalVariable(HEADER[i]));
        }

        Table<Long, Variable, Value> data = HashBasedTable.create();
        for (int i = 0; i < ROWS.length; ++i) {
            for (int j = 0; j < HEADER.length; ++j) {
                data.put((long) i, variableMap.get(j), new CategoricalValue(ROWS[i][j], variableMap.get(j)));
            }
        }
        BaseDataset ds = new BaseDataset(data);
        ds.setTarget(new BinaryTarget(variableMap.get(TARGET_HEADER_INDEX), new CategoricalValue(TARGET_VALUE, variableMap.get(TARGET_HEADER_INDEX))));
        return ds;
    }

}
