package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.LocalDateTimePersistanceConverter;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GKislin
 * 06.03.2015.
 */
@Entity
@Table(name="MEALS")
@NamedQueries({
        @NamedQuery(name=UserMeal.ALL_SORTED, query="SELECT m FROM UserMeal m WHERE m.user.id=?1 ORDER BY m.id DESC "),
        @NamedQuery(name=UserMeal.GET_BY_ID, query="SELECT m FROM UserMeal m WHERE m.user.id=?1 AND m.id=?2  "),
        @NamedQuery(name=UserMeal.DELETE, query="DELETE FROM UserMeal m WHERE m.user.id=?1 AND m.id=?2"),
        @NamedQuery(name=UserMeal.DEL_ALL, query="DELETE FROM UserMeal m WHERE m.user.id=?1"),
        @NamedQuery(name=UserMeal.BETWEEN, query="SELECT m FROM UserMeal m WHERE m.dateTime>=?1 AND m.dateTime<=?2 AND m.user.id=?3 ORDER BY m.dateTime DESC "),
})
public class UserMeal extends BaseEntity {
    public static final String ALL_SORTED="UserMeal.getAllSorted";
    public static final String DELETE="UserMeal.delete";
    public static final String GET_BY_ID="UserMeal.getById";
    public static final String DEL_ALL="UserMeal.deleteAll";
    public static final String BETWEEN="UserMeal.between";

    @Column(name = "dateTime",nullable = false)
    @Convert(converter = LocalDateTimePersistanceConverter.class)
    protected LocalDateTime dateTime;

    @Column(name = "description",nullable = false)
    protected String description;

    @Column(name = "calories",nullable = false)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public UserMeal() {
    }

    public UserMeal(UserMeal meal) {
        this(meal.id, meal.dateTime, meal.description, meal.calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + TimeUtil.toString(dateTime) + ", '" + description + "', calories:" + calories + ')';
    }

    public void setUser(User user) {
        this.user = user;
    }

}
