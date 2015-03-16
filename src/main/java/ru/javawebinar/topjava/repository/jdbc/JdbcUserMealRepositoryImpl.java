package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private  SimpleJdbcInsert insertMeals;

    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeals= new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("meal_id");
    }

    @Override
    public UserMeal save(UserMeal UserMeal, int userId) {

        MapSqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("meal_id",UserMeal.getId())
                .addValue("description",UserMeal.getDescription())
                .addValue("calories",UserMeal.getCalories())
                .addValue("dateTime",UserMeal.getDateTime())
                .addValue("user_meal_id",userId);

        if (UserMeal.isNew()) {
            Number newKey = insertMeals.executeAndReturnKey(sqlParameterSource);
            UserMeal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE MEALS SET description=:description, calories=:calories, dateTime=:dateTime, " +
                            "user_meal_id=:user_meal_id WHERE meal_id=:id", sqlParameterSource);
        }
        return UserMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE meal_id=? AND user_meal_id=?",id,userId)!=0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return jdbcTemplate.queryForObject("SELECT *FROM meals WHERE meal_id=? AND user_meal_id=?",ROW_MAPPER,id,userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT*FROM meals",ROW_MAPPER);
    }

    @Override
    public void deleteAll(int userId) {
        jdbcTemplate.update("DELETE FROM meals");

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT*FROM meals WHERE (dateTime BETWEEN ? AND ?) AND user_meal_id=?",ROW_MAPPER,userId,startDate,endDate);
    }
}
