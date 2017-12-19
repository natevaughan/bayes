package com.natevaughan.bayes.predictor

import com.google.common.base.Stopwatch
import groovy.util.logging.Slf4j
import org.junit.Test
import spock.lang.Specification

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * Author Nate Vaughan
 */
@Slf4j
class ThreadingTest {

    @Test
    void foo() {
        Stopwatch master = Stopwatch.createStarted()

        ExecutorService exec = Executors.newCachedThreadPool()
        List<Future<String>> stringFutures = []
        for (int i in 1..10) {
            stringFutures << exec.submit( new StopwatchCallable(i))
        }

        log.debug("collecting futures")

        for (Future<String> stringFuture : stringFutures) {
            try {
                String message = stringFuture.get(1000, TimeUnit.MILLISECONDS)
                log.debug("retrieved message in ${master.elapsed(TimeUnit.MILLISECONDS)}: \"$message\"")
            } catch (e) {
                log.debug(e.getClass().getSimpleName())
            }
        }


    }

}
