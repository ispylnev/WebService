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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetIndexPageServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(GetIndexPageServlet.class);
    private static String index = "WEB-INF/index.jsp";
    private Map<Integer,User> users;

    @Override
    public void init() throws ServletException {
        final Object users = getServletContext().getAttribute("users");
        if(users == null || !(users instanceof ConcurrentHashMap)){
            throw new IllegalStateException();
        }else {
            this.users = (ConcurrentHashMap<Integer,User>) users;
        }

        logger.info("***Servlet init***");
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res)
            throws ServletException, IOException{
        req.setAttribute("users",users.values());
//        return jsp page
        req.getRequestDispatcher(index).forward(req,res);
        logger.info("**Work**");
    }

    private boolean reqIsValid(HttpServletRequest req){
        final String name = req.getParameter("name");
        final String age = req.getParameter("age");
        return name != null && name.length() > 0
                && age!=null && age.length()>0
                && age.matches("\\d+");

    }

    @Override
    public void destroy() {
        logger.info("***Servlet destroy***");
    }
}
