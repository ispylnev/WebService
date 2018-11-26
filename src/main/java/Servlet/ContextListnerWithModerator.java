package Servlet;

import DAO.Dao;
import model.Moderator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicReference;

import static model.Moderator.ROLE.ADMIN;

public class ContextListnerWithModerator implements ServletContextListener {
    private AtomicReference<Dao> userModerator;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        userModerator = new AtomicReference<>(new Dao());
        userModerator.get().add(new Moderator(1,"Admin".toLowerCase(),"1",ADMIN));
        userModerator.get().add(new Moderator(1,"User".toLowerCase(),"1",ADMIN));
        final ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("dao",userModerator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        userModerator = null;

    }
}
