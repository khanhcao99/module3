package controller;

import model.Product;
import service.impl.CategoryService;
import service.impl.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet {
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
            case "update":

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

                break;
            case "update":

                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:

               break;
        }
    }

    public void displayProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> products = productService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        request.setAttribute("products", products);
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
        ArrayList<Product> products = productService.findAll();
        id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        request.setAttribute("id", id);
        request.setAttribute("products", products);
        dispatcher.forward(request, response);
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productService.delete(id);
        ArrayList<Product> products = productService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        request.setAttribute("products", products);
        request.setAttribute("id", null);
        dispatcher.forward(request, response);
    }

}
