package DAO;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {
    private CategoryRepository categoryRepository = new CategoryRepository();

    private static MyConnection myConnection = new MyConnection();

    private final String SELECT_ALL_PRODUCT = "call findAllProduct();";

    private final String INSERT_PRODUCT = "call insertProduct(?, ?, ?, ?, ?, ?);";

    private final String UPDATE_PRODUCT_BY_ID = "call updateProductById(?, ?, ?, ?, ?, ?, ?);";

    private final String DELETE_PRODUCT_BY_ID = "call deleteProductById(?);";

    private final String SELECT_PRODUCT_BY_ID = "call findProductById(?);";

    private final String SELECT_PRODUCT_BY_ID_CATEGORY = "call findProductByIdCategory(?);";

    private final String SELECT_PRODUCT_BY_NAME = "call findProductByName(?);";

    public ProductRepository() {
    }

    public void addProduct(Product product) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int id_category = resultSet.getInt("id_category");
                Category category = categoryRepository.findCategoryById(id_category);
                Product product = new Product(id, name, price , quantity, color, description, category);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return products;
    }

    public Product findProductById(int id){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int id_category = resultSet.getInt("id_category");
                Category category = categoryRepository.findCategoryById(id_category);
                Product product = new Product(id, name, price , quantity, color, description, category);
                return product;
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deleteProductById(int id){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public void updateProductById(Product product){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Product> findProductByIdCategory(int idc){
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_CATEGORY);
            preparedStatement.setInt(1, idc );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int id_category = resultSet.getInt("id_category");
                Category category = categoryRepository.findCategoryById(id_category);
                Product product = new Product(id, name, price , quantity, color, description, category);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return products;
    }

    public ArrayList<Product> findProductByName(String namep){
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, "%" + namep + "%" );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int id_category = resultSet.getInt("id_category");
                Category category = categoryRepository.findCategoryById(id_category);
                Product product = new Product(id, name, price , quantity, color, description, category);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return products;
    }

}
