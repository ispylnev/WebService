package filter;

import DAO.Dao;
import model.Moderator;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        final HttpServletResponse httpServletResponse= (HttpServletResponse) res;
        final String login = req.getParameter("login");
        final String pasw = req.getParameter("password");
        @SuppressWarnings(value = "unchecked")
        final AtomicReference<Dao> moderator = (AtomicReference<Dao>) req.getServletContext().getAttribute("moderator");
        final HttpSession httpSession = httpServletRequest.getSession();

//        loggin
        if(nonNull(httpSession)&&nonNull(httpSession.getAttribute("login"))
        &&nonNull(httpSession.getAttribute("password"))){
            final Moderator.ROLE role = (Moderator.ROLE) httpSession.getAttribute("role");

            moveToMenu(httpServletRequest,httpServletResponse,role);
            filterChain.doFilter(req, res);



        }else if (moderator.get().userIsExist(login,pasw)){

            final Moderator.ROLE role = moderator.get().getRoleUser(login,pasw);

            httpServletRequest.getSession().setAttribute("login",login.toLowerCase());
            httpServletRequest.getSession().setAttribute("password",pasw);
            httpServletRequest.getSession().setAttribute("role",role);
            moveToMenu(httpServletRequest,httpServletResponse,role);
        }else {
            moveToMenu(httpServletRequest,httpServletResponse,Moderator.ROLE.UNKNOWN);
        }

        filterChain.doFilter(req, res);

    }

    @Override
    public void destroy() {
    }


    @SuppressWarnings(value = "unchecked")
    public void  moveToMenu(final HttpServletRequest req, final HttpServletResponse res,
                           final Moderator.ROLE role) throws ServletException, IOException {
        if (role.equals(Moderator.ROLE.ADMIN)){
//            req.getRequestDispatcher("WEB-INF/index.jsp").forward(req,res);
//            res.sendRedirect(req.getContextPath() + "/s");
        }else if (role.equals(Moderator.ROLE.USER)){
            req.getRequestDispatcher("WEB-INF/TestJson.jsp").forward(req,res);
            res.sendRedirect(req.getContextPath()+"/");
        }else if (role.equals(Moderator.ROLE.UNKNOWN)){
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,res);
        }
    }

}
