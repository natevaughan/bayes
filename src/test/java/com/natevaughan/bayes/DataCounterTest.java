package com.natevaughan.bayes;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nate on 1/14/17.
 */
public class DataCounterTest {

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
    public void testGolfData() throws DataCounterException {
        Target target = new Target(TARGET_COLUMN_HEADER, TARGET_VALUE);
        DataCounter dataCounter = DataCounter.create(HEADER, CLASSES, target);
        dataCounter.process(ROWS);
        Tuple2[] vals = new Tuple2[] {
                new Tuple2<String, String>("outlook", "rainy"),
                new Tuple2<String, String>("temp", "hot"),
                new Tuple2<String, String>("humidity", "high"),
                new Tuple2<String, String>("windy", "true")
        };
        boolean prediction = dataCounter.getPrediction(vals);
        System.out.println(prediction);
    }

}
