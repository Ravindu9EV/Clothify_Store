package repository.custom.impl;

import entity.OrderEntity;
import entity.SuperEntity;
import entity.OrderDetailEntity;
import repository.custom.OrderDetailDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetailEntity entity) {
        String SQL="INSERT INTO orderDetail Values(?,?,?,?) ";
        System.out.println(entity);
        try {
            return CrudUtil.execute(SQL,
                    entity.getOrderID(),
                    entity.getProductID(),
                    entity.getQuantity(),
                    entity.getDiscount()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean update(OrderDetailEntity entity) {

        String SQL="UPDATE orderDetail SET  ProductID=?, Quantity=?, Discount=? WHERE OrderID='"+entity.getOrderID()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getProductID(),entity.getQuantity(),entity.getDiscount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetailEntity> findAll() {
        String SQL="Select * FROM orderDetail";
        List<OrderDetailEntity> orderDetailEntities=new ArrayList<>();
        try {
            ResultSet resultSet=CrudUtil.execute(SQL);
           while (resultSet.next()){
               orderDetailEntities.add( new OrderDetailEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailEntities;
    }

    @Override
    public SuperEntity search(String id) {
        String SQL="SELECT * FROM orderDetail WHERE OrderID='"+id+"'";
        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while (rst.next()){
                return new OrderDetailEntity(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {

        String SQL="Delete FROM orderDetail WHERE OrderID='"+id+"'";

        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<OrderDetailEntity> findAll(String id) {
        String SQL="SELECT * FROM orderDetail WHERE OrderID='"+id+"'";

        List<OrderDetailEntity> orderDetailEntities=new ArrayList<>();
        try {
            ResultSet resultSet=CrudUtil.execute(SQL);
            while (resultSet.next()){
                orderDetailEntities.add(new OrderDetailEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailEntities;
    }
}
