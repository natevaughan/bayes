package com.natevaughan.bayes.variable

import com.natevaughan.bayes.variable.BooleanValue
import com.natevaughan.bayes.variable.CategoricalVariable
import com.natevaughan.bayes.variable.Value
import groovy.transform.CompileStatic
import java.util.*

/**
 * Created by nate on 2/23/17.
 */
@CompileStatic
class LinesToValuesProcessor {

    static Collection<Value> convert(String line) {
        return convert(line.split(" "))
    }

    static Collection<Value> convert(String[] words) {
        return convert(Arrays.asList(words))
    }

    static Collection<Value> convert(Collection<String> words) {
        List<Value> values = new ArrayList<Value>()
        for (String word in words) {
            values << new BooleanValue(true, new CategoricalVariable(word))
        }
        return values
    }

    static Collection<Value> stripNonAlphaAndConvert(String line) {
        return convert(line.replaceAll("[^A-Za-z0-9 ]", ""))
    }
}
