package Servlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetIndexPageServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(GetIndexPageServlet.class);
    private static String index = "WEB-INF/index.jsp";


    @Override
    public void init() throws ServletException {
        logger.info("***Servlet init***");
        System.out.print("slg");

    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res)
            throws ServletException, IOException{
        logger.info("**Work**");
        req.getRequestDispatcher(index).forward(req,res);

    }

    @Override
    public void destroy() {
        logger.info("***Servlet destroy***");
    }
}
