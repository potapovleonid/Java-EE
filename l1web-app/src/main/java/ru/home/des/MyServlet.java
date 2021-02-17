package ru.home.des;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class MyServlet implements Servlet {

    private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("Initial procces MyServlet");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request to MyServlet");
        servletResponse.getWriter().println("<h1> Hello from my servlet</h1>");
    }

    @Override
    public String getServletInfo() {
        logger.info("get info from MyServlet");
        return null;
    }

    @Override
    public void destroy() {
        logger.info("Delete MyServlet");
    }

}
