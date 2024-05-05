package org.bluett.entity;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseConstants {

    public static Path BASE_PATH = Paths.get(System.getProperty("user.dir"));

    public static Path IMG_PATH = BASE_PATH.resolve("img");
}
