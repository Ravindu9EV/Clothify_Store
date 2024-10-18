package repository;

import entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository <T> extends SuperDao{
    boolean save(T entity) throws SQLException;
    boolean update(T entity);
    List<T> findAll() throws SQLException;
    SuperEntity search(String id);
    boolean delete(String id);
    }