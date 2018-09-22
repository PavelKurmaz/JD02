package com.gmail.kurmazpavel.filters;

import javax.servlet.*;
import java.io.IOException;

public class FilterUTF8 implements Filter {

    private String encode;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encode = filterConfig.getInitParameter("encode");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String encoding = req.getCharacterEncoding();
        if (encoding == null || !encoding.equals(encode))
            req.setCharacterEncoding(encode);
        encoding = resp.getCharacterEncoding();
        if (encoding == null || !encoding.equals(encode))
            resp.setCharacterEncoding(encode);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        encode = null;
    }
}
