package org.bluett.entity;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemCache {
    public static final Path PROJECT_PATH = Paths.get(System.getProperty("user.dir"));
    public static final Path CACHE_PATH = PROJECT_PATH.resolve("cache");
    public static final Path DATABASE_PATH = CACHE_PATH.resolve("mdfat.db");
}
