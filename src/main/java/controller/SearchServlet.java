package controller;

import model.Category;
import model.Product;
import service.impl.CategoryService;
import service.impl.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Xử lý Search Tiếng Việt
        request.setCharacterEncoding("UTF-8");
        String namep = request.getParameter("txt");
        ArrayList<Product> products = productService.findProductByName(namep);
        ArrayList<Category> categories = categoryService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.setAttribute("value", namep);
        dispatcher.forward(request, response);
    }
}
