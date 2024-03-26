package org.bluett.thread;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.config.ThreadPoolsConfig;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Log4j2
public class ThreadPools {
    public final static ThreadPoolExecutor TEST_CASE_THREAD_POOL = new ThreadPoolExecutor(
            ThreadPoolsConfig.CORE_POOL_SIZE,
            ThreadPoolsConfig.MAXIMUM_POOL_SIZE,
            ThreadPoolsConfig.KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(ThreadPoolsConfig.QUEUE_SIZE),
            new BasicThreadFactory.Builder()
                    .daemon(true)
                    .namingPattern("TestCaseThread-%d")
                    .priority(Thread.MAX_PRIORITY)
                    .uncaughtExceptionHandler((t, e) -> log.error("Uncaught exception in thread {}", t.getName(), ExceptionUtils.getRootCause(e)))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public final static ThreadPoolExecutor TEST_SUITE_THREAD_POOL = new ThreadPoolExecutor(
            ThreadPoolsConfig.CORE_POOL_SIZE,
            ThreadPoolsConfig.MAXIMUM_POOL_SIZE,
            ThreadPoolsConfig.KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(ThreadPoolsConfig.QUEUE_SIZE),
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
