package repository.custom.impl;


import entity.SuperEntity;
import entity.SupplierEntity;
import repository.custom.SupplierDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        System.out.println(entity);
        String SQL="INSERT INTO Supplier VALUES(?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getCompany(),entity.getProductID(),entity.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(SupplierEntity entity) {

        String SQL="UPDATE Supplier SET name=?,  company=?, productID=?, email=? WHERE id='"+entity.getId()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getName(),entity.getCompany(),entity.getProductID(),entity.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SupplierEntity> findAll() {
        String SQL="SELECT * FROM Supplier";
        List<SupplierEntity>  supplierEntities=new ArrayList<>();
        try {
            ResultSet rst= CrudUtil.execute(SQL);
            while (rst.next()){
                supplierEntities.add(new SupplierEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierEntities;
    }

    @Override
    public SuperEntity search(String id) {
        String SQL="Select * FROM Supplier Where id='"+id+"'";
        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while ((rst.next())){
                return new SupplierEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String SQL="Delete From Supplier Where id='"+id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
