package repository.custom.impl;

import entity.ProductEntity;
import repository.custom.ProductDao;
import util.DaoType;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
        return false;
    }

    @Override
    public boolean update(ProductEntity entity) {
        return false;
    }

    @Override
    public List<ProductEntity> findAll() {
        return List.of();
    }

    @Override
    public DaoType search(String id) {
        return null;
    }
}
