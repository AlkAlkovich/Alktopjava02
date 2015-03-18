package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final UserMeal USER_MEAL=new UserMeal(100002,LocalDateTime.of(2015,01,01,00,00),"meal1",10);
    public static UserMeal MEAL1= new UserMeal(100003, LocalDateTime.of(2015,01,02,00,00), "meal2", 11);
    public static UserMeal MEAL2= new UserMeal(100004, LocalDateTime.of(2015,01,03,00,00), "meal3", 12);
    public static UserMeal MEAL3= new UserMeal(100005, LocalDateTime.of(2015,01,04,00,00), "meal4", 13);
//    public static UserMeal MEAL4= new UserMeal(null, LocalDateTime.of(2015,01,04,00,00), "meal4", 1012210);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(
            new Function<UserMeal, String>() {
                @Override
                public String apply(UserMeal meal) {
                    return meal.toString();
                }
            });

    public static class TestMeals extends UserMeal{
        public TestMeals(UserMeal userMeal) {
            this(userMeal.getId(),userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories());
        }

        public TestMeals(Integer id, LocalDateTime dateTime, String description, int calories) {
            super(id, dateTime, description, calories);
        }

        public UserMeal asMeal(){
            return new UserMeal(this);
        }

        @Override
        public String toString() {
            return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}
