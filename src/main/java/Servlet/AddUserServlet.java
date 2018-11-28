package Servlet;

import Utils.Utils;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AddUserServlet extends HttpServlet {
  final static  Logger logger = LogManager.getLogger(AddUserServlet.class);
    private Map<Integer, User> users;
    private AtomicInteger id;


    @Override
    public void init() throws ServletException {
        Object users = getServletContext().getAttribute("users");
        if(users == null || !(users instanceof ConcurrentHashMap)){
            throw new IllegalStateException();
        }else this.users = (ConcurrentHashMap<Integer,User>)users;
        id = new AtomicInteger(2);
        logger.info("***ServletAddUser init***");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        if(Utils.rqIsValid(req)){
            final String name = req.getParameter("name");
            final String age = req.getParameter("age");
            final Integer id  = this.id.getAndIncrement();
            User user = new User(name, Integer.valueOf(age),id);
            users.put(id,user);
        }
//        redirect URL
        resp.sendRedirect(req.getContextPath() + "/");


    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
