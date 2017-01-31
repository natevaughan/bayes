package com.natevaughan.bayes.processor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

/**
 * Created by nate on 1/14/17.
 */
public class DatasetPreProcessorTest {

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
        Table<Long, String, String> data = createMockData();
    }

    private Table<Long, String, String> createMockData() {

        Table<Long, String, String> data = HashBasedTable.create();
        for (int i = 0; i < ROWS.length; ++i) {
            for (int j = 0; j < HEADER.length; ++j) {
                data.put((long) i, HEADER[j], ROWS[i][j]);
            }
        }
        return data;
    }

}
