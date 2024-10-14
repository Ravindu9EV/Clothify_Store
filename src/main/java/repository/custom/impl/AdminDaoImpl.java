package repository.custom.impl;

import entity.AdminEntity;
import repository.custom.AdminDao;
import util.DaoType;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity entity) {
        return false;
    }

    @Override
    public boolean update(AdminEntity entity) {
        return false;
    }

    @Override
    public List<AdminEntity> findAll() {
        return List.of();
    }

    @Override
    public DaoType search(String id) {
        return null;
    }
}
