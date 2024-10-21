package repository.custom.impl;

import db.DBConnection;
import dto.Order;
import dto.OrderDetail;
import entity.SuperEntity;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import service.ServiceFactory;
import service.custom.OrderDetailService;
import service.custom.OrderService;
import util.CrudUtil;
import util.DaoType;
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
    @Override
    public boolean save(OrderEntity order) throws SQLException {
        System.out.println(order);

        String SQL="INSERT INTO Orders VALUES(?,?,?,?,?)";

        return CrudUtil.execute(SQL,order.getId(),order.getUserID(),order.getCustomerID(),order.getOrderDate(),order.getPaymentType());
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
    public List<OrderEntity> findAll() throws SQLException {
        String SQL="Select * FROM Orders ";

        List<OrderEntity> orderEntities=new ArrayList<>();

            List<OrderDetail> orderDetails=orderDetailService.getAll();
            ResultSet rst= CrudUtil.execute(SQL);
            while(rst.next()){
                orderEntities.add(new OrderEntity(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),orderDetails));
            }

        return orderEntities;
    }

    @Override
    public SuperEntity search(String id) {
        String SQL="Select * From Orders WHERE OrderID='"+id+"'";
        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while (rst.next()){
                List<OrderDetail> orderDetails=new ArrayList<>();
                for(OrderDetailEntity orderDetailEntity:orderDetailDao.findAll(id)){
                    if(orderDetailEntity!=null){
                        orderDetails.add(new ModelMapper().map(orderDetailEntity,OrderDetail.class));
                    }
                };

                return new OrderEntity(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),orderDetails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public boolean delete(String id) {
        String SQL="Delete From Orders WHERE OrderID='"+id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Order search(String id, String customerID) {
        String SQL="Select * From Orders WHERE OrderID='"+id+"' and CustomerID='"+customerID+"'";

        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while (rst.next()){
                List<OrderDetail> orderDetails=new ArrayList<>();
                for (OrderDetailEntity orderDetailEntity:orderDetailDao.findAll(id)){
                   if(orderDetailEntity!=null) {
                       orderDetails.add(new ModelMapper().map(orderDetailEntity,OrderDetail.class));
                   }
                }
                return new Order(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),orderDetails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
