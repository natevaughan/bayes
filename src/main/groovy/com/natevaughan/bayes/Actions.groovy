package com.natevaughan.bayes

/**
 * Created by nate on 2/12/17.
 */
enum Actions {
    QUIT,
    OPTIMIZE,
    TARGETS,
    TRAIN,
    NEW,
    PREDICT

    boolean is(String str) {
        return this.toString().equalsIgnoreCase(str);
    }
}
