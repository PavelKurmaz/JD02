package filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


public class LogFilter implements Filter{
    private final Logger logger = LogManager.getLogger(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("LogFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        logger.debug("#INFO " + new Date() + " URL = " + req.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.debug("LogFilter destroyed");
    }
}
