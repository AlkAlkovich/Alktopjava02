package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final UserMeal USER_MEAL=new UserMeal(BaseEntity.START_SEQ,LocalDateTime.now(),"s",2122);

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
