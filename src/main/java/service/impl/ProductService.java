package service.impl;

import DAO.ProductRepository;
import model.Product;
import service.CRUD;

import java.util.ArrayList;

public class ProductService implements CRUD<Product> {
    private final ProductRepository productRepository = new ProductRepository();

    public ProductService() {

    }

    @Override
    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void add(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.updateProductById(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteProductById(id);
    }

    public ArrayList<Product> findProductByIdCategory(int id){
        return productRepository.findProductByIdCategory(id);
    }

    public ArrayList<Product> findProductByName(String name){
        return productRepository.findProductByName(name);
    }

}
