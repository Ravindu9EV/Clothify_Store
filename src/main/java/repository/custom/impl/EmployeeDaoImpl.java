package repository.custom.impl;

import entity.EmployeeEntity;
import repository.custom.EmployeeDao;
import util.DaoType;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return List.of();
    }

    @Override
    public DaoType search(String id) {
        return null;
    }
}
