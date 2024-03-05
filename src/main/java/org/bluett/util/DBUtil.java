package org.bluett.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.SystemCache;

import java.io.InputStream;
import java.util.Properties;

public class DBUtil {
    private static final Logger log = LogManager.getLogger(DBUtil.class);
    private static final String URL = "jdbc:sqlite:" + SystemCache.DATABASE_PATH;
    private static SqlSessionFactory FACTORY;

    static {
        String resource = "mybatis-config.xml";
        try (InputStream stream = Resources.getResourceAsStream(resource)) {
            FACTORY = new SqlSessionFactoryBuilder().build(stream,
                    fillDatabase(null, URL, null, null));
        }catch (Exception e){
            log.error("数据库初始化失败", e);
        }
    }

    private DBUtil(){}

    public static SqlSession getSqlSession(){
        return FACTORY.openSession();
    }

    private static Properties fillDatabase(String driver, String url, String username, String password){
        Properties properties = new Properties();
        if(driver != null) properties.setProperty("driver", driver);
        if(url != null) properties.setProperty("url", url);
        if(username != null) properties.setProperty("username", username);
        if(password != null) properties.setProperty("password", password);
        return properties;
    }
}
