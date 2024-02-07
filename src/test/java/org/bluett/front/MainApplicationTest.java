package org.bluett;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
import com.github.joonasvali.naturalmouse.support.DefaultMouseMotionNature;
import com.github.joonasvali.naturalmouse.util.FactoryTemplates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class MainApplicationTest {

    private final static Logger log = LogManager.getLogger(MainApplicationTest.class);

    @Test
    void test1() throws InterruptedException {
        DefaultMouseMotionNature mouseMotionNature = new DefaultMouseMotionNature();
        mouseMotionNature.setReactionTimeBaseMs(3000);
        MouseMotionFactory mouseMotionFactory = FactoryTemplates.createFastGamerMotionFactory(mouseMotionNature);
        mouseMotionFactory.move(50, 50);
    }

    @Test
    void test2() throws InterruptedException {
    }

}