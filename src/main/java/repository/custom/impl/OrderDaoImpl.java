package repository.custom.impl;

import db.DBConnection;
import dto.Order;
import dto.OrderDetail;
import entity.SuperEntity;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import repository.custom.SupplierDao;
import service.ServiceFactory;
import service.custom.OrderDetailService;
import service.custom.OrderService;
import util.CrudUtil;
import util.DaoType;
import util.HibernateUtil;
import util.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    OrderDetailService orderDetailService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAIL);
    OrderDetailDao orderDetailDao= DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAIL);
    ProductDao productDao= DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    @Override
    public boolean save(OrderEntity order) throws SQLException {
        System.out.println(order);

        //Connection connection= DBConnection.getInstance().getConnection();
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(order);

        if((orderDetailDao.save(order.getOrderDetails()))){
            if(productDao.updateStock(order.getOrderDetails())){
                session.getTransaction().commit();
                session.close();
                return true;
            }
        }


        session.getTransaction().rollback();
        return false;
//
//                connection.setAutoCommit(false);
//                String SQL = "INSERT INTO orders VALUES(?,?,?,?,?)";
//                PreparedStatement pst = connection.prepareStatement(SQL);
//                pst.setObject(1, order.getId());
//                pst.setObject(2, order.getUserID());
//                pst.setObject(3, order.getCustomerID());
//                pst.setObject(4, order.getOrderDate());
//                pst.setObject(5, order.getPaymentType());
//                boolean isOrderAdd = pst.executeUpdate() > 0;
//                OrderEntity orderEntity = null;
//                if (order != null) {
//                    orderEntity = new ModelMapper().map(order, OrderEntity.class);
//                }
//
//                if (isOrderAdd) {
//                    boolean isOrderDetailAdd = orderDetailService.addOrderDetail(orderEntity.getOrderDetails());
//                    System.out.println("****--->" + isOrderDetailAdd);
//                    if (isOrderDetailAdd) {
//
//                        List<OrderDetailEntity> orderDetailEntities=new ArrayList<>();
//                        for(OrderDetailEntity orderDetailEntity:order.getOrderDetails()){
//                            if(orderDetailEntity!=null){
//                                orderDetailEntities.add(orderDetailEntity);
//                            }
//                        }
//                        boolean pd=productDao.updateStock(orderDetailEntities);
//                        System.out.println("orderDao->sav()->productDao->updatestock()");
//                        if (pd) {
//                            System.out.println("Addddddd");
//                            connection.commit();
//                            return true;
//                        }
//                    }
//                }
//
//            }finally {
//                    connection.setAutoCommit(true);
//            }
//        connection.rollback();
//        return false;
        }

    @Override
    public boolean update(OrderEntity entity) {
        String SQL="Update Orders SET UserID=?, CustomerID=?, OrderDate=?,PaymentType=? WHERE OrderID='"+entity.getId()+"'";

        try {
            return CrudUtil.execute(SQL,entity.getUserID(),entity.getCustomerID(),entity.getOrderDate(),entity.getPaymentType());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderEntity> findAll()  {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        List<OrderEntity> orderEntities=session.createQuery("Select a From OrderEntity a",OrderEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return orderEntities;

//        String SQL="Select * FROM Orders ";
//
//        List<OrderEntity> orderEntities=new ArrayList<>();
//
//            List<OrderDetail> orderDetails=orderDetailService.getAll();
//            ResultSet rst= CrudUtil.execute(SQL);
//            while(rst.next()){
//                orderEntities.add(new OrderEntity(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),orderDetails));
//            }
//
//        return orderEntities;
    }

    @Override
    public SuperEntity search(String id) {
        List<OrderEntity> orderEntities=findAll();
        for(OrderEntity orderEntity:orderEntities){
            if(orderEntity.getId().equals(id)){
                return orderEntity;
            }
        }


//        String SQL="Select * From Orders WHERE OrderID='"+id+"'";
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while (rst.next()){
//                List<OrderDetail> orderDetails=new ArrayList<>();
//                for(OrderDetailEntity orderDetailEntity:orderDetailDao.findAll(id)){
//                    if(orderDetailEntity!=null){
//                        orderDetails.add(new ModelMapper().map(orderDetailEntity,OrderDetail.class));
//                    }
//                };
//
//                return new OrderEntity(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),orderDetails);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }


    @Override
    public boolean delete(String id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.remove(search(id));
        session.getTransaction().commit();
        session.close();
        return true;
//        String SQL="Delete From Orders WHERE OrderID='"+id+"'";
//        try {
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }


    @Override
    public Order search(String id, String customerID) {

//        Session session=HibernateUtil.getSession();
//        session.getTransaction().begin();
//        String hql="SELECT od from orderentity WHERE od.OrderID=:newID and CustomerID=:custID";
//        OrderEntity orderEntity=session.createQuery(hql).setParameter("newID",id).setParameter("CustID",customerID).getFirstResult();
//        session.getTransaction().commit();

//        String SQL="Select * From Orders WHERE OrderID='"+id+"' and CustomerID='"+customerID+"'";
//
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while (rst.next()){
//                List<OrderDetail> orderDetails=new ArrayList<>();
//                for (OrderDetailEntity orderDetailEntity:orderDetailDao.findAll(id)){
//                   if(orderDetailEntity!=null) {
//                       orderDetails.add(new ModelMapper().map(orderDetailEntity,OrderDetail.class));
//                   }
//                }
//                return new Order(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),orderDetails);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}
