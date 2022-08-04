package controller;

import model.Category;
import model.Product;
import service.impl.CategoryService;
import service.impl.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet {
    public static final String PRODUCT_SERVLET = "product-servlet";
    private final ProductService productService = new ProductService();

    private  final CategoryService categoryService = new CategoryService();

    private int id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":

                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                viewDeleteProduct(request, response);
                break;
            case "sreach":

                break;
            default:
                displayProduct(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProductPost(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:

               break;
        }
    }

    public void displayProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> categories = categoryService.findAll();
        ArrayList<Product> products = productService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }
    public void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> categories = categoryService.findAll();
        ArrayList<Product> products = productService.findAll();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(name, price, quantity, color, description, category);
        productService.add(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        dispatcher.forward(request, response);
    }
    public void viewDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int result = JOptionPane.showConfirmDialog(null,
//                "Bạn có muốn xóa toàn bộ giỏ hàng không?",
//                "Xác nhận",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE);
//        if (result == JOptionPane.YES_OPTION) {
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("managerProduct.jsp");
//            requestDispatcher.forward(request, response);
//        } else {
//
//        }
//        request.setAttribute("message", "Delete succesfully!");
        id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
        request.setAttribute("product", product);
        dispatcher.forward(request, response);
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            productService.delete(id);
            request.setAttribute("message", "Xóa thành công");
            dispatcher = request.getRequestDispatcher("delete.jsp");
        }
        dispatcher.forward(request, response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        ArrayList <Category> categories = categoryService.findAll();
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product", product);
            request.setAttribute("categories", categories);
            dispatcher = request.getRequestDispatcher("edit.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void updateProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        id = Integer.parseInt(request.getParameter("id"));
        String namep = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(id, namep, price, quantity, color, description, category);
        RequestDispatcher  dispatcher;
        ArrayList <Category> categories = categoryService.findAll();
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            productService.update(product);
            request.setAttribute("product", product);
            request.setAttribute("categories", categories);
            request.setAttribute("message", "Cập nhập thành công");
            dispatcher = request.getRequestDispatcher("edit.jsp");
        }
        dispatcher.forward(request, response);
    }
}
