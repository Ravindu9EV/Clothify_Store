package repository.custom.impl;

import entity.*;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import util.CrudUtil;
import util.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    //OrderDao orderDao= DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    //ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    @Override
    public boolean save(OrderDetailEntity entity) {
        String SQL="INSERT INTO orderDetail Values(?,?,?,?) ";
        System.out.println(entity);
        try {
            //OrderDetailKey orderDetailKey=new OrderDetailKey(entity.getOrderID(), entity.getProductID());
            //System.out.println(orderDetailKey.getOrderID()+"\n\n"+orderDetailKey.getProductID());
           boolean deetAdd= CrudUtil.execute(SQL,
                    entity.getOrderID(),
                    entity.getProductID(),
                    entity.getQuantity(),
                    entity.getDiscount()
            );
            System.out.println(entity
                    .getOrderID());
            System.out.println("detailDao->save()"+deetAdd);
            return deetAdd;
        } catch (SQLException e) {
            System.out.println("detailDao->"+e);
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean update(OrderDetailEntity entity) {

        String SQL="UPDATE orderDetail SET  ProductID=?, Quantity=?, Discount=? WHERE OrderID='"+entity.getOrderID()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getOrderID(),entity.getProductID(),entity.getQuantity(),entity.getDiscount());
        } catch (SQLException e) {
            System.out.println("detailDao->uodate()"+e);
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
               //orderDetailEntities.add( new OrderDetailEntity(new OrderDetailKey(resultSet.getString(1),resultSet.getString(2)),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
               orderDetailEntities.add( new OrderDetailEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
//
//               OrderEntity orderEntity=(OrderEntity) orderDao.search(rst.getString(1));
//               ProductEntity productEntity=(ProductEntity) productDao.search(rst.getString(2));
//               orderDetailEntities.add(new OrderDetailEntity(new OrderDetailKey(orderEntity.getId(),productEntity.getId()),orderEntity,productEntity,rst.getInt(3),rst.getDouble(4)));
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
//                OrderEntity orderEntity=(OrderEntity) orderDao.search(rst.getString(1));
//                ProductEntity productEntity=(ProductEntity) productDao.search(rst.getString(2));
                //return new OrderDetailEntity(new OrderDetailKey(rst.getString(1),rst.getString(2)),rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
               return new OrderDetailEntity(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));

            }
        } catch (SQLException e) {
            System.out.println("detailDao->search() "+e);
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
               //orderDetailEntities.add(new OrderDetailEntity(new OrderDetailKey(resultSet.getString(1),resultSet.getString(2)),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
                orderDetailEntities.add(new OrderDetailEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));

                //                OrderEntity orderEntity=(OrderEntity) orderDao.search(rst.getString(1));
//                ProductEntity productEntity=(ProductEntity) productDao.search(rst.getString(2));
//                orderDetailEntities.add(new OrderDetailEntity(new OrderDetailKey(orderEntity.getId(),productEntity.getId()),rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailEntities;
    }
}
