package org.bluett.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Log4j2
public class ThreadPoolConfig {
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    public static final int MAXIMUM_POOL_SIZE = 100;
    public static final long KEEP_ALIVE_TIME = 60;
    public static final int QUEUE_SIZE = 100;
    
    public final static ThreadPoolExecutor TEST_CASE_THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(QUEUE_SIZE),
            new BasicThreadFactory.Builder()
                    .daemon(true)
                    .namingPattern("TestCaseThread-%d")
                    .priority(Thread.MAX_PRIORITY)
                    .uncaughtExceptionHandler((t, e) -> log.error("Uncaught exception in thread {}", t.getName(), ExceptionUtils.getRootCause(e)))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public final static ThreadPoolExecutor TEST_SUITE_THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(QUEUE_SIZE),
            new BasicThreadFactory.Builder()
                    .daemon(true)
                    .namingPattern("TestSuiteThread-%d")
                    .priority(Thread.MAX_PRIORITY)
                    .uncaughtExceptionHandler((t, e) -> log.error("Uncaught exception in thread {}", t.getName(), ExceptionUtils.getRootCause(e)))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    static {
        TEST_CASE_THREAD_POOL.prestartAllCoreThreads();
        TEST_SUITE_THREAD_POOL.prestartAllCoreThreads();
    }
}
