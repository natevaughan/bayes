package com.natevaughan.bayes.variable

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import groovy.transform.CompileStatic

/**
 * Created by rm on 2/19/17.
 */
@CompileStatic
class ValueFactory {
    static Table<String, String, Value> createdVars

    private static ValueFactory instance

    private ValueFactory() {
        createdVars = HashBasedTable.create()
    }

    static ValueFactory getInstance() {
        if (!instance) {
            instance = new ValueFactory()
        }
        return instance
    }

    static create(String name, boolean bool) {
        if (!createdVars.row(name)){
            Variable newVar = new CategoricalVariable(name)
            BooleanValue value = new BooleanValue(bool, newVar)
            newVar.addValue(value)
            createdVars.put(name, bool.toString(), value)
        }
//        if (!createdVars.get(name).getValue())
    }
}
