package org.bluett.helper;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bluett.MainApplication;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
public class MybatisHelper {
    public static final Path DATABASE_PATH = Paths.get(System.getProperty("user.dir")).resolve("db").resolve("mdfat.db");
    private static final URL MAPPER_URL = MainApplication.class.getResource("/mapper");
    private static final String DB_URL = "jdbc:sqlite:" + DATABASE_PATH;
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final SqlSessionFactory FACTORY = getSqlSessionFactory();

    private MybatisHelper(){}

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
