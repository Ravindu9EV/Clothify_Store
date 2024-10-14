package repository.custom.impl;

import entity.SupplierEntity;
import repository.custom.SupplierDao;
import util.DaoType;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        return false;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        return false;
    }

    @Override
    public List<SupplierEntity> findAll() {
        return List.of();
    }

    @Override
    public DaoType search(String id) {
        return null;
    }
}
