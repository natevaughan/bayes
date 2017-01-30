package com.natevaughan.bayes;

/**
 * Created by nate on 1/14/17.
 */
public class TupleFactory {

    public static Tuple3<String, Integer, String> create(String key, Integer id, String value) {
        return new Tuple3(key, id, value);
    }
}
