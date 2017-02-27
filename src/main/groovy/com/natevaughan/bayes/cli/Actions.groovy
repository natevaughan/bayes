package com.natevaughan.bayes.cli

/**
 * Created by nate on 2/12/17.
 */
enum Actions {
    QUIT,
    OPTIMIZE,
    LIST,
    TRAIN,
    NEW,
    PREDICT

    boolean is(String str) {
        return this.toString().equalsIgnoreCase(str);
    }
}
