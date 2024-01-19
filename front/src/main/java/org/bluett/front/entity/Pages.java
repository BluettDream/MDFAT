package org.bluett.front.entity;

import org.bluett.front.MainApplication;
import org.bluett.front.util.PathUtil;

import java.net.URL;
import java.nio.file.Path;

public enum Pages{
    INDEX(0, "首页", MainApplication.class.getResource("views/index.fxml"), PathUtil.IMG_PATH.resolve("index.png")),
    SETTING(5, "设置", MainApplication.class.getResource("views/setting.fxml"), PathUtil.IMG_PATH.resolve("setting.png")),
    ABOUT(10, "关于", MainApplication.class.getResource("views/about.fxml"), PathUtil.IMG_PATH.resolve("about.png"))
    ;
    private final int index;
    private final String pageName;
    private final URL pageURL;
    private final Path iconPath;

    Pages(int index, String pageName, URL pageURL, Path iconPath){
        this.index = index;
        this.pageName = pageName;
        this.pageURL = pageURL;
        this.iconPath = iconPath;
    }

    public int getIndex() {
        return index;
    }

    public String getPageName() {
        return pageName;
    }

    public URL getPageURL() {
        return pageURL;
    }

    public Path getIconPath() {
        return iconPath;
    }
}
