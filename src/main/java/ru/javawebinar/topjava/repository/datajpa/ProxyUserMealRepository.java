package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alk
 * Date: 30.03.15
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Transactional
    UserMeal save(UserMeal userMeal);

    List<UserMeal> findByDateTimeGreaterThanAndDateTimeLessThanAndUserIdOrderByDateTimeDesc(LocalDateTime after, LocalDateTime before, int user_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal m WHERE m.user.id=:id")
    int deleteAll(@Param("id") int userId);

    List<UserMeal> findByUserIdOrderByDateTimeDesc(int user_id);

   UserMeal findOne(Integer id);
}
