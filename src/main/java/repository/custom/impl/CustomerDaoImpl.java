package repository.custom.impl;

import entity.AdminEntity;
import entity.CustomerEntity;
import entity.SuperEntity;
import repository.custom.CustomerDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) {
        String SQL="INSERT INTO Customer VALUES(?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getEmail(),entity.getContact());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CustomerEntity entity) {
        String SQL="UPDATE Customer SET name=?, contact=?, email=?, password=? WHERE id='"+entity.getId()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getName(),entity.getEmail(),entity.getContact());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerEntity> findAll() {
        String SQL="SELECT * FROM Customer";
        List<CustomerEntity>  adminEntities=new ArrayList<>();
        try {
            ResultSet rst= CrudUtil.execute(SQL);
            while (rst.next()){
                adminEntities.add(new CustomerEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminEntities;
    }

    @Override
    public SuperEntity search(String SQL) {
       /// String SQL="Select * FROM Customer Where id='"+id+"'";
        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while ((rst.next())){
                return new CustomerEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4));

            }
          } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String SQL="Delete From Customer Where id='"+id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
