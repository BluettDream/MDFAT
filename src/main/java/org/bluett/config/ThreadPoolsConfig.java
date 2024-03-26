package org.bluett.config;

import lombok.Data;

@Data
public class ThreadPoolsConfig {
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    public static final int MAXIMUM_POOL_SIZE = 100;
    public static final long KEEP_ALIVE_TIME = 60;
    public static final int QUEUE_SIZE = 100;
}
