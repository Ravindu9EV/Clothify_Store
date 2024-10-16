package repository;

import util.DaoType;

import java.util.List;

public interface CrudRepository <T> extends SuperDao{
    boolean save(T entity);
    boolean update(T entity);
    List<T> findAll();
    DaoType search(String id);
    }