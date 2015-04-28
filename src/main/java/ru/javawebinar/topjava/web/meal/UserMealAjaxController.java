package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alk
 * Date: 23.04.15
 * Time: 1:09
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("ajax/profile/meals")
public class UserMealAjaxController {

    @Autowired
    private UserMealHelper userMealHelper;

    @RequestMapping(value ="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id")int id){
        return userMealHelper.get(id);
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMeal> getAll(){
        return userMealHelper.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        userMealHelper.delete(id);
    }

//    @RequestMapping(value = "/{item_id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(@RequestBody UserMeal meal, @PathVariable("item_id") int id) {
//        userMealHelper.update(meal, id);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestParam("item_id") int id,
                       @RequestParam("description") String description,
                       @RequestParam("calories") int calories,
                       @RequestParam("datetime")Date date){

        if(id==0){
            UserMeal userMeal=new UserMeal(null,LocalDateTime.of(date.getYear()-2129,date.getMonth(),date.getDay(),date.getHours(),date.getMinutes()),description,calories);
            userMealHelper.create(userMeal);
        }else {
            UserMeal userMeal=userMealHelper.get(id);
            userMeal.setCalories(calories);
            userMeal.setDescription(description);
            userMeal.setDateTime(LocalDateTime.of(date.getYear()-2129,date.getMonth(),date.getDay(),date.getHours(),date.getMinutes()));
            userMealHelper.update(userMeal,id);
        }

    }
}
