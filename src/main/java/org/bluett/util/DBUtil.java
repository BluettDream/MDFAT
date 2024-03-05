package org.bluett.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.MainApplication;
import org.bluett.entity.SystemCache;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class DBUtil {
    private static final Logger log = LogManager.getLogger(DBUtil.class);
    private static final String DB_URL = "jdbc:sqlite:" + SystemCache.DATABASE_PATH;
    private static final URL MAPPER_URL = MainApplication.class.getResource("/mapper");
    private static SqlSessionFactory FACTORY;

    static {
        String resource = "mybatis-config.xml";
        try (InputStream stream = Resources.getResourceAsStream(resource)) {
            FACTORY = new SqlSessionFactoryBuilder().build(stream, fillProperties());
        }catch (Exception e){
            log.error("数据库初始化失败", e);
        }
    }

    private DBUtil(){}

    public static SqlSession getSqlSession(){
        return FACTORY.openSession();
    }

    private static Properties fillProperties(){
        if(MAPPER_URL == null) throw new RuntimeException("mapper文件加载失败");
        Properties properties = new Properties();
        properties.setProperty("dbUrl", DB_URL);
        properties.setProperty("mapperUrl", "file://" + MAPPER_URL.getPath());
        return properties;
    }
}
