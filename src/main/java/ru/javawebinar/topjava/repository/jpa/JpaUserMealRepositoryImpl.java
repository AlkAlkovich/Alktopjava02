package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 20.03.2015.
 */
@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {

        if (userMeal.isNew()) {
            User user = em.getReference(User.class, userId);
            userMeal.setUser(user);
            em.persist(userMeal);
            return userMeal;
        } else {
            UserMeal meal=em.getReference(UserMeal.class,userMeal.getId());
            if (meal.getUser().getId() != userId) {
                return null;
            } else {
                userMeal.setUser(em.getReference(User.class, userId));
                em.merge(userMeal);
                return userMeal;
            }
        }


//        if (UserMeal.getUser() == null) {
//            if (UserMeal.isNew()) {
//                User user = em.find(User.class, userId);
//                UserMeal.setUser(user);
//               em.persist(UserMeal);
//            } else {
//                User user = em.find(User.class, userId);
//                UserMeal.setUser(user);
//                if(UserMeal.getUser().getId()!=userId){
//                    System.out.println("np");
//                    return null;
//                }
//                em.merge(UserMeal);
//            }
//
//            return UserMeal;
//        } else return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE).setParameter(1, userId).setParameter(2, id).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId){

       List<UserMeal> userMeals=em.createNamedQuery(UserMeal.GET_BY_ID,UserMeal.class).setParameter(1,userId).setParameter(2,id).getResultList();
        if(!userMeals.isEmpty()){
            if(userMeals.get(0).getUser().getId()==userId){
                return userMeals.get(0);
            }else return null;
        }

//        UserMeal userMeal = em.getReference(UserMeal.class, id);
//        if (userMeal.getUser().getId() == userId) {
//            return userMeal;
//        } else return null;
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter(1, userId).getResultList();
    }

    @Override
    @Transactional
    public void deleteAll(int userId) {
        em.createNamedQuery(UserMeal.DEL_ALL).setParameter(1, userId).executeUpdate();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.BETWEEN, UserMeal.class).setParameter(1, startDate).setParameter(2, endDate).setParameter(3, userId).getResultList();
    }
}
