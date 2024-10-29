package repository.custom.impl;

import entity.*;
import org.hibernate.Session;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import util.CrudUtil;
import util.DaoType;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    //OrderDao orderDao= DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    //ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    public boolean save(List<OrderDetailEntity> orderDetailEntities) {
        for(OrderDetailEntity entity:orderDetailEntities){
            if(!save(entity)){
                return false;
            }
        }
        return true;
    }


        @Override
    public boolean save(OrderDetailEntity entity) {
        int count=0;
        if (entity!=null){

                Session session=HibernateUtil.getSession();
                session.getTransaction().begin();
                session.persist(entity);
                session.getTransaction().commit();

                session.close();



                return true;

        }
//        String SQL="INSERT INTO orderDetail Values(?,?,?,?) ";
//        System.out.println(entity);
//        try {
//            OrderDetailKey orderDetailKey=new OrderDetailKey(entity.getOrderID(), entity.getProductID());
//            //System.out.println(orderDetailKey.getOrderID()+"\n\n"+orderDetailKey.getProductID());
//           boolean deetAdd= CrudUtil.execute(SQL,
//                    entity.getOrderID(),
//                    entity.getProductID(),
//                    entity.getQuantity(),
//                    entity.getDiscount()
//            );
//            System.out.println(entity
//                    .getOrderID());
//            System.out.println("detailDao->save()"+deetAdd);
//            return deetAdd;
//        } catch (SQLException e) {
//            System.out.println("detailDao->"+e);
//            throw new RuntimeException(e);
//        }

        return false;
    }



    @Override
    public boolean update(OrderDetailEntity entity) {

        if(entity!=null){
            try {


                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                session.persist(entity);
                session.getTransaction().commit();
                session.close();

                return true;
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return false;
//        String SQL="UPDATE orderDetail SET  ProductID=?, Quantity=?, Discount=? WHERE OrderID='"+entity.getOrderID()+"'";
//        try {
//            return CrudUtil.execute(SQL,entity.getOrderID(),entity.getProductID(),entity.getQuantity(),entity.getDiscount());
//        } catch (SQLException e) {
//            System.out.println("detailDao->uodate()"+e);
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<OrderDetailEntity> findAll() {
        try {
            Session session = HibernateUtil.getSession();

            session.getTransaction().begin();
            List<OrderDetailEntity> orderDetailEntities = session.createQuery(String.valueOf(OrderDetailEntity.class)).list();
            return orderDetailEntities;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
//        String SQL="Select * FROM orderDetail";
//        List<OrderDetailEntity> orderDetailEntities=new ArrayList<>();
//        try {
//            ResultSet resultSet=CrudUtil.execute(SQL);
//           while (resultSet.next()){
//               orderDetailEntities.add( new OrderDetailEntity(new OrderDetailKey(resultSet.getString(1),resultSet.getString(2)),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
//             //  orderDetailEntities.add( new OrderDetailEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
////
////               OrderEntity orderEntity=(OrderEntity) orderDao.search(rst.getString(1));
////               ProductEntity productEntity=(ProductEntity) productDao.search(rst.getString(2));
////               orderDetailEntities.add(new OrderDetailEntity(new OrderDetailKey(orderEntity.getId(),productEntity.getId()),orderEntity,productEntity,rst.getInt(3),rst.getDouble(4)));
//           }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return orderDetailEntities;
    }

    @Override
    public SuperEntity search(String id) {
        try{
            Session session=HibernateUtil.getSession();
            session.getTransaction().begin();
            OrderDetailEntity orderDetailEntity= (OrderDetailEntity) session.byId(id);
            session.getTransaction().commit();
            session.close();
            if(orderDetailEntity!=null){
                return orderDetailEntity;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
//        String SQL="SELECT * FROM orderDetail WHERE OrderID='"+id+"'";
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while (rst.next()){
////                OrderEntity orderEntity=(OrderEntity) orderDao.search(rst.getString(1));
////                ProductEntity productEntity=(ProductEntity) productDao.search(rst.getString(2));
//                //return new OrderDetailEntity(new OrderDetailKey(rst.getString(1),rst.getString(2)),rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
//               return new OrderDetailEntity(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
//
//            }
//        } catch (SQLException e) {
//            System.out.println("detailDao->search() "+e);
//            throw new RuntimeException(e);
//        }
//        return null;
    }

    @Override
    public boolean delete(String id) {
        try{
            Session session=HibernateUtil.getSession();
            session.getTransaction().begin();
            OrderDetailEntity orderDetailEntity=(OrderDetailEntity)search(id);
            session.remove(orderDetailEntity);
            session.getTransaction().commit();
            session.close();
            return orderDetailEntity!=null ? true : false;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
//        String SQL="Delete FROM orderDetail WHERE OrderID='"+id+"'";
//
//        try {
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }


    @Override
    public List<OrderDetailEntity> findAll(String id) {
        try {
            Session session = HibernateUtil.getSession();

            session.getTransaction().begin();
            List<OrderDetailEntity> orderDetailEntities = (List<OrderDetailEntity>) session.find(OrderDetailEntity.class, new ArrayList<>());
            session.getTransaction().commit();
            session.close();
            return orderDetailEntities;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
//        String SQL="SELECT * FROM orderDetail WHERE OrderID='"+id+"'";
//
//        List<OrderDetailEntity> orderDetailEntities=new ArrayList<>();
//        try {
//            ResultSet resultSet=CrudUtil.execute(SQL);
//            while (resultSet.next()){
//               //orderDetailEntities.add(new OrderDetailEntity(new OrderDetailKey(resultSet.getString(1),resultSet.getString(2)),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
//                orderDetailEntities.add(new OrderDetailEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4)));
//
//                //                OrderEntity orderEntity=(OrderEntity) orderDao.search(rst.getString(1));
////                ProductEntity productEntity=(ProductEntity) productDao.search(rst.getString(2));
////                orderDetailEntities.add(new OrderDetailEntity(new OrderDetailKey(orderEntity.getId(),productEntity.getId()),rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return orderDetailEntities;
    }

}
