package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * GKislin
 * 09.03.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {

    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);
    @PostConstruct
    public void postConstruct() {
        LOG.info("PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("PreDestroy");
    }

    @Override
    public UserMeal save(UserMeal UserMeal, int userId) {
        LOG.info("save "+UserMeal+"id: "+userId);
        return UserMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete id "+id+"userid: "+userId);
        return id!=0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        LOG.info("getid "+id+"userid"+id);

        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        LOG.info("getAll"+userId);
        return Collections.emptyList();
    }

    @Override
    public void deleteAll(int userId) {
        LOG.info("deleteALl"+userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        LOG.info("getBetween "+startDate+" "+endDate+"userid "+userId);

        return Collections.emptyList();
    }
}
