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


public class DeletUserServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(DeletUserServlet.class);
    Map<Integer, User> users;

    @Override
    public void init() throws ServletException {
       Object users = getServletContext().getAttribute("users");
       if (users == null ||!(users instanceof ConcurrentHashMap)){
           throw new IllegalStateException();
        }else {
           this.users = (ConcurrentHashMap<Integer,User>)users;
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (Utils.idIsNumber(req)){
            users.remove(Integer.valueOf(req.getParameter("id")));
        }else {
            logger.error("cannot delete user");
        }
        resp.sendRedirect(req.getContextPath()+"/");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
