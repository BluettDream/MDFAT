package org.bluett;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.dao.TestImageDAO;
import org.bluett.helper.MybatisHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

@Log4j2
class MainApplicationTest {
    private static final SqlSession SESSION = MybatisHelper.getSqlSession();
    private static final TestImageDAO testImageDAO = new TestImageDAO(SESSION);

    @Test
    void testInsert() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testUpdate(){
    }

    @Test
    void test(){
        System.out.println(FileUtils.current().getAbsolutePath());
    }

    @AfterAll
    static void testSelect() {

    }
}