package com.natevaughan.bayes.predictor

import com.google.common.base.Stopwatch
import groovy.util.logging.Slf4j

import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/**
 * @author Nate Vaughan
 */
@Slf4j
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

        log.debug(str)

        return str
    }
}
