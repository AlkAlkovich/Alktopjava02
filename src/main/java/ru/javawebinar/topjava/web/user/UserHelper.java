package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.PasswordUtil;

import java.util.List;

/**
 * User: gkislin
 */
@Component
public class UserHelper {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserHelper.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        LOG.info("create " + user);
        return service.save(PasswordUtil.getEncoded(user));
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        LOG.info("update " + user);
        user.update(id);
        service.update(PasswordUtil.getEncoded(user));
    }

    public User getByMail(String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enable) {
        LOG.info("enable " + id);
        service.enable(id, enable);
    }
}
