package com.natevaughan.bayes;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

/**
 * Created by nate on 1/20/17.
 */
public class TableTest {
    Table<Long, String, Variable<Integer>> table = HashBasedTable.create();

    @Test
    public void guavaHashBasedTableTest() {
        table.put(1L, "abc", new Variable<>("abc", new Target("abc","def")));
        table.get(1L, "abc");
    }
}
