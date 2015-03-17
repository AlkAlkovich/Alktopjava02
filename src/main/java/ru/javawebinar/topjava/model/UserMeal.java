package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMeal extends BaseEntity {
    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    private User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMeal(UserMeal testMeals) {
        this(testMeals.getId(),testMeals.getDateTime(),testMeals.getDescription(),testMeals.getCalories());
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
        return user;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + TimeUtil.toString(dateTime) + ", '" + description + "', calories:" + calories + ')';
    }

    public void setUser(User user) {
        this.user = user;
    }


}
