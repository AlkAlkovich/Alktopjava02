package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.util.DbPopulator;

/**
 * Created with IntelliJ IDEA.
 * User: Alk
 * Date: 16.03.15
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration({
        "classpath:spring/spring-test.xml",
        "classpath:spring/spring-db.xml"
})
public class UserMealServiceTest {

    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServiceTest.class);
    @Autowired
  private UserMealService userMealService;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
         LOG.info("populating");
         dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        userMealService.


    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetBetween() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testDeleteAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }
}
