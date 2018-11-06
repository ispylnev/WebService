package Servlet;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetIndexPageServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(GetIndexPageServlet.class);
    private static String index = "WEB-INF/index.jsp";
    List<User> users;




    @Override
    public void init() throws ServletException {
        users = new CopyOnWriteArrayList<>();
        users.add(new User("Test",1));
        logger.info("***Servlet init***");

    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res)
            throws ServletException, IOException{
        req.setAttribute("users",users);
        req.getRequestDispatcher(index).forward(req,res);
        logger.info("**Work**");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      req.setCharacterEncoding("UTF8");
      if(!reqIsValid(req)) {
          doGet(req, resp);
          logger.error("Invalid req");
      }
      final String name = req.getParameter("name");
      final String age = req.getParameter("age");
      final User user = new User(name,Integer.valueOf(age));
      users.add(user);
      doGet(req,resp);
      logger.info("User add");


    }

    private boolean reqIsValid(HttpServletRequest req){
        final String name = req.getParameter("name");
        final String age = req.getParameter("age");
        logger.info(name,age);
        return name != null && name.length() > 0
                && age!=null && age.length()>0
                && age.matches("[+]?\\d+");

    }

    @Override
    public void destroy() {
        logger.info("***Servlet destroy***");
    }
}
