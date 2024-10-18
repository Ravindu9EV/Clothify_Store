package repository.custom.impl;

import entity.AdminEntity;
import entity.SuperEntity;
import repository.custom.AdminDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity entity) {

        String SQL="INSERT INTO Admin VALUES(?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getContact(),entity.getEmail(),entity.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(AdminEntity entity) {
         String SQL="UPDATE Admin SET name=?, contact=?, email=?, password=? WHERE id='"+entity.getId()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getName(),entity.getContact(),entity.getEmail(),entity.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AdminEntity> findAll() {
        String SQL="SELECT * FROM Admin";
        List<AdminEntity>  adminEntities=new ArrayList<>();
        try {
            ResultSet rst= CrudUtil.execute(SQL);
            while (rst.next()){
                adminEntities.add(new AdminEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminEntities;
    }

    @Override
    public SuperEntity search(String id) {
        String SQL="Select * FROM Admin Where id='"+id+"'";

        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while(rst.next()){
                return new AdminEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String SQL="Delete From Admin Where id='"+id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
