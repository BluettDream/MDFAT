package org.bluett;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.dao.TestImageDAO;
import org.bluett.helper.MybatisHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
class MainApplicationTest {
    private static final SqlSession SESSION = MybatisHelper.getSqlSession();
    private static final TestImageDAO testImageDAO = new TestImageDAO(SESSION);

    @Test
    void testInsert() {
        testImageDAO.save(TestImageDTO.builder()
                .confidence(BigDecimal.valueOf(0.9253))
                .link("https://www.baidu.com")
                .build());
        SESSION.commit();
    }

    @Test
    void testDelete() {
        testImageDAO.delete(1L);
        SESSION.commit();
        testImageDAO.deleteBatch(List.of(2L, 3L, 4L, 5L));
        SESSION.commit();
    }

    @Test
    void testUpdate(){
        testImageDAO.update(TestImageDTO.builder()
                .id(6L)
                .confidence(BigDecimal.valueOf(0.9253))
                .link("https://www.abc.com")
                .build());
        SESSION.commit();
    }

    @Test
    void test(){
        System.out.println(FileUtils.current().getAbsolutePath());
    }

    @AfterAll
    static void testSelect() {

    }
}