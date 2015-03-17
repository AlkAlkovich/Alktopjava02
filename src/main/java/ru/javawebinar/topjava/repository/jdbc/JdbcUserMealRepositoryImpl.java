package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    private static final LoggerWrapper LOG = LoggerWrapper.get(JdbcUserMealRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcUserRepositoryImpl userRepository;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private  SimpleJdbcInsert insertMeals;

    public JdbcUserMealRepositoryImpl() {
    }

    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeals= new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal UserMeal, int userId) {

        MapSqlParameterSource sqlParameterSource=new MapSqlParameterSource()
                .addValue("id",UserMeal.getId())
                .addValue("description", UserMeal.getDescription())
                .addValue("calories",UserMeal.getCalories())
                .addValue("datetime", UserMeal.getDateTime())
                .addValue("user_by_id", userId);

        if (UserMeal.isNew()) {
            Number newKey = insertMeals.executeAndReturnKey(sqlParameterSource);
            UserMeal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET description = :description, calories = :calories, " +
                            "user_by_id = :user_by_id WHERE id = :id", sqlParameterSource);
        }
        return UserMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_by_id=?",id,userId)!=0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        LOG.info("get "+id+" userid "+userId);
        return (UserMeal)jdbcTemplate.queryForObject("SELECT meals.id,meals.description,meals.calories,meals.datetime FROM public.meals WHERE meals.id=? AND meals.user_by_id=?",new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserMeal userMeal=new UserMeal();
                userMeal.setId(rs.getInt("ID"));

                userMeal.setDatetime(LocalDateTime.parse(rs.getString("datetime"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                userMeal.setDescription(rs.getString("description"));
                userMeal.setCalories(rs.getInt("calories"));

                return userMeal;
            }
        },id,userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT id,description,calories,datetime FROM meals",new RowMapper<UserMeal>() {
            @Override
            public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
              UserMeal  userMeal=new UserMeal();
                userMeal.setId(rs.getInt("ID"));

                userMeal.setDatetime(LocalDateTime.parse(rs.getString("datetime"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                userMeal.setDescription(rs.getString("description"));
                userMeal.setCalories(rs.getInt("calories"));
                userMeal.setUser(userRepository.get(userId));
                return userMeal;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    @Override
    public void deleteAll(int userId) {
        jdbcTemplate.update("DELETE FROM meals");

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Timestamp timestamp=Timestamp.valueOf(startDate);
        Timestamp timestamp1=Timestamp.valueOf(endDate);
        return jdbcTemplate.query("SELECT id,description,calories,datetime FROM meals WHERE (datetime BETWEEN ? AND ?) AND user_by_id=?",new RowMapper<UserMeal>() {
            @Override
            public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserMeal  userMeal=new UserMeal();
                userMeal.setId(rs.getInt("ID"));

                userMeal.setDatetime(LocalDateTime.parse(rs.getString("datetime"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                userMeal.setDescription(rs.getString("description"));
                userMeal.setCalories(rs.getInt("calories"));
                userMeal.setUser(userRepository.get(userId));
                return userMeal;
            }
        },timestamp,timestamp1,userId);
    }
}
