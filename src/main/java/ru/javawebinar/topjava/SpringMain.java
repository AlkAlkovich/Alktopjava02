package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")) {
            System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
            UserMealRestController adminController = appCtx.getBean(UserMealRestController.class);
            System.out.println( adminController.get(100002));
            System.out.println(adminController.create(new UserMeal(100004, LocalDateTime.of(2015,01,02,11,1),"testim",12345)));
            System.out.println(adminController.getAll());
            System.out.println(adminController.getBetween(LocalDateTime.of(2015,01,02,10,1),LocalDateTime.of(2015,03,02,10,1)));
        }
    }
}
