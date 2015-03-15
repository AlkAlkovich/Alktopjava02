package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Alk
 * Date: 15.03.15
 * Time: 3:52
 * To change this template use File | Settings | File Templates.
 */
public class UserMealServlet  extends HttpServlet{

    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("redirect usermeals");
        req.getRequestDispatcher("/usermeallist.jsp").forward(req,resp);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
}
