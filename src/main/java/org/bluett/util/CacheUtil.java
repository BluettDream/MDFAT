package org.bluett.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CacheUtil {
    public static final Path PROJECT_PATH = Paths.get(System.getProperty("user.dir"));
    public static final Path CACHE_PATH = PROJECT_PATH.resolve("cache");
    public static final Path DATABASE_PATH = CACHE_PATH.resolve("mdfat.db");
}
