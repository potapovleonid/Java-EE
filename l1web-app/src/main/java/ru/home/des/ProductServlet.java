package ru.home.des;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.home.des.persist.Product;
import ru.home.des.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);
    private ProductRepository repository;

    @Override
    public void init() throws ServletException {
        this.repository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (repository == null){
            throw new ServletException("ProductRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", "Catalog>Product>");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("<h1>Product page</h1>");

        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", repository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Product product = repository.findById(id);
            if (product == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/new")) {
            req.setAttribute("product", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex){
                return;
            }

            repository.deleteById(id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = null;
        try {
            String sid = req.getParameter("id");
            if (sid != null && !sid.isEmpty()){
                id = Long.parseLong(req.getParameter("id"));
            }
        }catch (NumberFormatException ex){
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal("price");
        } catch (NumberFormatException e){
            return;
        }
        repository.saveOrUpdate(new Product(id, req.getParameter("title"), req.getParameter("description"), price));
        resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }
}
