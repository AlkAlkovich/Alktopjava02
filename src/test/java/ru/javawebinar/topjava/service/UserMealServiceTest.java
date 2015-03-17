package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL;
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
        UserMeal userMeal = userMealService.get(BaseEntity.START_SEQ, LoggedUser.id());
        MATCHER.assertEquals(userMeal,USER_MEAL);

    }

    @Test
    public void testDelete() throws Exception {

        userMealService.delete(BaseEntity.START_SEQ,LoggedUser.id());
        MATCHER.assertListEquals(Collections.singletonList(USER_MEAL),userMealService.getAll(LoggedUser.id()));
    }

    @Test
    public void testGetBetween() throws Exception {

       List<UserMeal> userMeals= userMealService.getBetween(LocalDateTime.of(2013,02,01,21,22),LocalDateTime.now(),LoggedUser.id());
        MATCHER.assertListEquals(Arrays.asList(USER_MEAL),userMeals);
    }

    @Test
    public void testGetAll() throws Exception {

         List<UserMeal> userMeals=userMealService.getAll(LoggedUser.id());
        MATCHER.assertListEquals(Arrays.asList(USER_MEAL),userMeals);
    }

    @Test
    public void testDeleteAll() throws Exception {
        userMealService.deleteAll(LoggedUser.id());
        MATCHER.assertListEquals(Collections.singletonList(USER_MEAL),userMealService.getAll(LoggedUser.id()));     //TODO WHY?

    }

    @Test
    public void testUpdate() throws Exception {

        MealTestData.TestMeals meals=new MealTestData.TestMeals(USER_MEAL);
        meals.setDescription("hren");
        userMealService.update(meals,LoggedUser.id());
        MATCHER.assertEquals(meals,userMealService.get(BaseEntity.START_SEQ,LoggedUser.id()));
    }

    @Test
    public void testSave() throws Exception {

        MealTestData.TestMeals meals=new MealTestData.TestMeals(BaseEntity.START_SEQ+1,LocalDateTime.now(),"fast",1231);
        UserMeal userMeal=userMealService.update(meals.asMeal(),LoggedUser.id());
        meals.setId(userMeal.getId());
        MATCHER.assertListEquals(Arrays.asList(USER_MEAL,meals),userMealService.getAll(LoggedUser.id()));
    }
}
