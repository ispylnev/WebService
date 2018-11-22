package Servlet;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       final String text =  req.getParameter("filterUtf8");
        logger.info(text);
        resp.sendRedirect(req.getContextPath() + "/");
    }


    @Override
    public void destroy() {
        logger.info("***Servlet destroy***");
    }
}
