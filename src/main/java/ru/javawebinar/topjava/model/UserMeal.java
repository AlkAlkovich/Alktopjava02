package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMeal extends BaseEntity {
    protected LocalDateTime datetime;

    protected String description;

    protected int calories;

    private User user_by_id;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime datetime, String description, int calories) {
        super(id);
        this.datetime = datetime;
        this.description = description;
        this.calories = calories;
    }


    public UserMeal(UserMeal testMeals) {
        this(testMeals.getId(),testMeals.getDateTime(),testMeals.getDescription(),testMeals.getCalories());
    }

    public LocalDateTime getDateTime() {
        return datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String meal) {
        this.description = meal;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user_by_id;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + TimeUtil.toString(datetime) + ", '" + description + "', calories:" + calories + ')';
    }

    public void setUser(User user) {
        this.user_by_id = user;
    }


}
