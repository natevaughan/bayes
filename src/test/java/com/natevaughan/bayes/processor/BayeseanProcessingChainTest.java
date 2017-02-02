package com.natevaughan.bayes.processor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.natevaughan.bayes.dataset.BaseDataset;
import com.natevaughan.bayes.dataset.Dataset;
import com.natevaughan.bayes.variable.BinaryTarget;
import com.natevaughan.bayes.variable.CategoricalValue;
import com.natevaughan.bayes.variable.CategoricalVariable;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.Variable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nate on 1/14/17.
 */
public class BayeseanProcessingChainTest {

    private final String TARGET_COLUMN_HEADER = "TARGET";
    private final String TARGET_VALUE = "yes";
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
        CategoricalProcessingChain chain = new CategoricalProcessingChain();
        chain.addProcessingStep(new BayeseanProcessor());
        chain.processAll(createMockDataset());
        Dataset predictions = chain.predictAll(createMockDataset());
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
        ds.setTarget(new BinaryTarget(variableMap.get(4)));
        return ds;
    }

}
