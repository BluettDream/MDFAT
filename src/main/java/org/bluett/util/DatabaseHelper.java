package org.bluett.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.MainApplication;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class DatabaseHelper {
    private static final Logger log = LogManager.getLogger(DatabaseHelper.class);
    private static final URL MAPPER_URL = MainApplication.class.getResource("/mapper");
    private static final String DB_URL = "jdbc:sqlite:" + CacheUtil.DATABASE_PATH;
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final SqlSessionFactory FACTORY = getSqlSessionFactory();

    private DatabaseHelper(){}

    public static SqlSession getSqlSession(){
        if(FACTORY == null) throw new RuntimeException("数据库初始化失败");
        return FACTORY.openSession();
    }

    private static SqlSessionFactory getSqlSessionFactory(){
        try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG)) {
            return new SqlSessionFactoryBuilder().build(stream, fillProperties());
        }catch (Exception e){
            log.error("数据库初始化失败", e);
        }
        return null;
    }

    private static Properties fillProperties(){
        if(MAPPER_URL == null) throw new RuntimeException("mapper文件加载失败");
        Properties properties = new Properties();
        properties.setProperty("dbUrl", DB_URL);
        properties.setProperty("mapperUrl", "file://" + MAPPER_URL.getPath());
        return properties;
    }
}
