package ru.home.des;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_header")
public class HeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String h = (String) req.getAttribute("pageHeader");
        resp.getWriter().println("<h1>" + h + "</h1>");
        resp.getWriter().println("<a href=\"l1web-app/main\">Main</a>");
        resp.getWriter().println("<a href=\"l1web-app/catalog\">Catalog</a>");
        resp.getWriter().println("<a href=\"l1web-app/product\">Product</a>");
        resp.getWriter().println("<a href=\"l1web-app/cart\">Cart</a>");
        resp.getWriter().println("<a href=\"l1web-app/order\">Order</a>");
    }
}