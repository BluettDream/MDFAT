package org.bluett.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.MainApplication;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {
    private static final Logger log = LogManager.getLogger(PathUtil.class);
    public static final Path PROJECT_PATH = Paths.get(System.getProperty("user.dir"));

    public static URL getViewLocation(String path) {
        URL url = null;
        try{
            url = MainApplication.class.getResource("/assets/" + path);
        }catch (Exception e){
            log.error(e);
        }
        return url;
    }

}
