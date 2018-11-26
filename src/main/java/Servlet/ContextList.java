package Servlet;

import Utils.Utils;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@WebListener
// With access all servlet
//fake database connect
public class ContextList implements ServletContextListener {
    Logger logger = LogManager.getLogger(ContextList.class);
    private Map<Integer, User> users;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
         final ServletContext context = servletContextEvent.getServletContext();
         users = new ConcurrentHashMap<>();
         final User user = Utils.creatTestUser(1,"Test",11);
         users.put(user.getId(),user);
//         init test user with start any servlet
        context.setAttribute("users",users);
        logger.info("contextListner RUN");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Destroy ContextListner");
    }
}
