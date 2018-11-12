package Servlet;

import Utils.Utils;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UpdateUserServlet extends HttpServlet {
    final String index = "/WEB-INF/update.jsp";
    Map<Integer,User> users;

    @Override
    public void init() throws ServletException {
     Object users = getServletContext().getAttribute("users");
     if (users == null || !(users instanceof ConcurrentHashMap)){
         throw new IllegalStateException();
     }else this.users = (ConcurrentHashMap<Integer,User>)users;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");
        if (Utils.rqIsInValid(id,users)){
            resp.sendRedirect(req.getContextPath()+"/");
        }
        User user = users.get(Integer.valueOf(id));
        req.getRequestDispatcher(index).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final User user = users.get(Integer.parseInt(id));
        user.setName(name);
        resp.sendRedirect(req.getContextPath() + "/");

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
