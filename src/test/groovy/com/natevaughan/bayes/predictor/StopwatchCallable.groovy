package com.natevaughan.bayes.predictor

import com.google.common.base.Stopwatch

import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/**
 * Author Nate Vaughan
 */
class StopwatchCallable implements Callable<String> {
    final int myname

    StopwatchCallable(int myname) {
        this.myname = myname
    }


    @Override
    String call() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted()

        Thread.sleep((long) (Math.random() * 10000))

        String str = "$myname success! in ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}"

        println(str)

        return str
    }
}
