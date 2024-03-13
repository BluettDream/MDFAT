package org.bluett;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
import com.github.joonasvali.naturalmouse.support.DefaultMouseMotionNature;
import com.github.joonasvali.naturalmouse.util.FactoryTemplates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.service.TestCaseService;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

class MainApplicationTest {

    @Test
    void testSQL(){
        TestCaseService service = new TestCaseService();

    }
}