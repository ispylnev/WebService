package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostJson extends HttpServlet {
    Logger logger = LogManager.getLogger(PostJson.class);
    final String index = "/WEB-INF/TestJson.jsp";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher(index).forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
       final String json = request.getParameter("data");

       User user = new ObjectMapper().readValue(json,User.class);
       final String id = String.valueOf(user.getId());
       final String name = user.getName();
       final String age = String.valueOf(user.getAge());
       logger.info(id, "\n" , "\n" , name, "\n", age);


    }
}
