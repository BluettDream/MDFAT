package org.bluett.front.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {
    public static final Path PROJECT_PATH = Paths.get(System.getProperty("user.dir"));
    public static final Path IMG_PATH = PROJECT_PATH.resolve("front/assets/img");
}
