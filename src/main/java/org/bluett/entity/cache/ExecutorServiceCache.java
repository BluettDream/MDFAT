package org.bluett.entity.cache;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Log4j2
public class ExecutorServiceCache {
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final long KEEP_ALIVE_TIME = 60;
    private static final int QUEUE_SIZE = 100;
    private static volatile ThreadPoolExecutor testCaseThreadPool;
    private static volatile ThreadPoolExecutor testSuiteThreadPool;

    public static ThreadPoolExecutor getTestCaseThreadPool() {
        if(testCaseThreadPool == null){
            synchronized (ExecutorServiceCache.class){
                if(testCaseThreadPool == null){
                    testCaseThreadPool = new ThreadPoolExecutor(
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
                }
            }
        }
        return testCaseThreadPool;
    }

    public static ThreadPoolExecutor getTestSuiteThreadPool() {
        if(testSuiteThreadPool == null){
            synchronized (ExecutorServiceCache.class){
                if(testSuiteThreadPool == null){
                    testSuiteThreadPool = new ThreadPoolExecutor(
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
                }
            }
        }
        return testSuiteThreadPool;
    }
}
