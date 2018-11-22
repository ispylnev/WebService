package Servlet;

import javax.servlet.*;
import java.io.IOException;

public class ReqEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        // передаем управление следующему фильтру
        filterChain.doFilter(req, res);


    }

    @Override
    public void destroy() {

    }
}
