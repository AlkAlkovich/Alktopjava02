package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {

    private static final Sort SORT_NAME_EMAIL = new Sort("id");
    @Autowired
    private ProxyUserMealRepository proxyUserMealRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User user = userRepository.get(userId);
        userMeal.setUser(user);

        if (userMeal.isNew()) {
            proxyUserMealRepository.save(userMeal);
        } else {
            if (get(userMeal.getId(), userId) == null) return null;
            proxyUserMealRepository.save(userMeal);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxyUserMealRepository.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal = proxyUserMealRepository.findOne(id);
        if (userMeal.getUser().getId() == userId) return userMeal;
        else return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {

        return proxyUserMealRepository.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public void deleteAll(int userId) {
        proxyUserMealRepository.deleteAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxyUserMealRepository.findByDateTimeGreaterThanAndDateTimeLessThanAndUserIdOrderByDateTimeDesc(startDate, endDate, userId);
    }
}
