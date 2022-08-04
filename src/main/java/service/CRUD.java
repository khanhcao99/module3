package service;

import java.util.ArrayList;

public interface CRUD<E> {
    ArrayList<E> findAll();

    void add(E e);

    E findById(int id);

    void update(E e);

    void delete(int id);
}
