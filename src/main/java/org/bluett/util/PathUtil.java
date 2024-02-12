package org.bluett.util;

import org.bluett.MainApplication;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {
    public static final Path PROJECT_PATH = Paths.get(System.getProperty("user.dir"));

    public static URL getResourcesURL(String path) {
        URL url = null;
        try{
            url = MainApplication.class.getResource("/" + path);
        }catch (Exception e){
            throw new RuntimeException("Error getting resources url of " + path);
        }
        if(url == null) throw new RuntimeException("Error getting resources url of " + path);
        return url;
    }

    public static URL getAssetsURL(String path) {
        return getResourcesURL("assets/" + path);
    }

    public static URL getFxmlLocation(String fxmlPath) {
        return getAssetsURL("views/" + fxmlPath + ".fxml");
    }
}
