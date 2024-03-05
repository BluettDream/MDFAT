package org.bluett;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.NodeEnum;
import org.bluett.entity.StageType;
import org.bluett.entity.SystemCache;
import org.bluett.util.ViewUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainApplication extends Application {
    private static final Logger log = LogManager.getLogger(MainApplication.class);

    @Override
    public void init() throws Exception {
        super.init();
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:" + SystemCache.CACHE_PATH.resolve("mdfat.db"));
            Statement stmt = conn.createStatement()){
            String sql = "create table user(ID INT PRIMARY KEY NOT NULL,NAME TEXT NOT NULL);";
            boolean flag = stmt.execute(sql);
            log.info(flag);
            log.debug("数据库连接成功");
        }catch (Exception e){
            log.error("数据库连接失败{}", e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = (Parent) ViewUtil.getNodeOrCreate(NodeEnum.MAIN);
        ViewUtil.getStageOrSave(StageType.PRIMARY, primaryStage);

        primaryStage.setTitle(ResourceBundle.getBundle("i18n").getString("title"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}