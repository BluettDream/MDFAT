package org.bluett.helper;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCaseCallableHelper {
    public static final AtomicInteger RUNNING_TEST_CASE_COUNT = new AtomicInteger(0);
    public static final AtomicBoolean SHUTDOWN = new AtomicBoolean(false);
    private TestCaseCallableHelper() {}
}
