package service.impl;

import DAO.CategoryRepository;
import model.Category;
import service.CRUD;

import java.util.ArrayList;

public class CategoryService implements CRUD<Category> {
    private final CategoryRepository categoryRepository = new CategoryRepository();

    public CategoryService() {

    }

    @Override
    public ArrayList<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void add(Category category) {
        categoryRepository.addCategory(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.updateCategoryById(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteCategoryById(id);
    }
}
