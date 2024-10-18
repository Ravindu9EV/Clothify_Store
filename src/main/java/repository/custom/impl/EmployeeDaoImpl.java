package repository.custom.impl;

import entity.AdminEntity;
import entity.EmployeeEntity;
import entity.SuperEntity;
import repository.custom.EmployeeDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
        String SQL="INSERT INTO Employee VALUES(?,?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getContact(),entity.getCompany(),entity.getEmail(),entity.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(EmployeeEntity entity) {

        String SQL="UPDATE Employee SET name=?, contact=?, company=?, email=?, password=? WHERE id='"+entity.getId()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getName(),entity.getContact(),entity.getCompany(),entity.getEmail(),entity.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeEntity> findAll() {

        String SQL="SELECT * FROM Employee";
        List<EmployeeEntity>  employeeEntities=new ArrayList<>();
        try {
            ResultSet rst= CrudUtil.execute(SQL);
            while (rst.next()){
                employeeEntities.add(new EmployeeEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeEntities;
    }

    @Override
    public SuperEntity search(String id) {
        String SQL="Select * FROM Employee Where id='"+id+"'";
        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while (rst.next()){
                return new EmployeeEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));

            }
           } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String SQL="Delete From Employee Where id='"+id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

