package Servlet;

import DAO.Dao;
import model.Moderator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;
import static model.Moderator.ROLE.ADMIN;
import static model.Moderator.ROLE.USER;
@WebListener
public class ContextListnerWithModerator implements ServletContextListener {
    private AtomicReference<Dao> userModerator;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext context = servletContextEvent.getServletContext();
        userModerator = new AtomicReference<>(new Dao());
        userModerator.get().add(new Moderator(1,"Admin".toLowerCase(),"1",ADMIN));
        userModerator.get().add(new Moderator(2,"User".toLowerCase(),"1",USER));
        context.setAttribute("moderator",userModerator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        userModerator = null;

    }


}
