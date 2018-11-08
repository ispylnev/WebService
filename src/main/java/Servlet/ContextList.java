package Servlet;

import Utils.Utils;
import model.User;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@WebListener
public class ContextList implements ServletContextListener {
    private Map<Integer, User> users;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
         final ServletContext context = servletContextEvent.getServletContext();
         users = new ConcurrentHashMap<>();
         final User user = Utils.creatTestUser(1,"Test",11);
         users.put(user.getId(),user);
//         init test user with start any servlet
        context.setAttribute("users",users);
        System.out.println("Init");


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.printf("DestroyContext");
    }
}
