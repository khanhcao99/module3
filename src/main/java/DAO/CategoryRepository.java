package DAO;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryRepository {
    private MyConnection myConnection = new MyConnection();

    private final String SELECT_ALL_CATEGORY = "call findAllCategory();";

    private final String INSERT_CATEGORY = "call insertCategory(?);";

    private final String UPDATE_CATEGORY_BY_ID = "call updateCategoryById(?, ?);";

    private final String DELETE_CATEGORY_BY_ID = "call deleteCategoryById(?);";

    private final String SELECT_CATEGORY_BY_ID = "call findCategoryById(?);";

    public CategoryRepository() {
    }

    public void addCategory(Category category) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Category> findAll() {
        ArrayList<Category> categorys  = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categorys.add(category);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return categorys;
    }

    public Category findCategoryById(int id){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                return category;
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deleteCategoryById(int id){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public void updateCategoryById(Category category){
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_BY_ID);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
