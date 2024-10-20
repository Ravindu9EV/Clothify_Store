package repository.custom.impl;

import db.DBConnection;
import dto.OrderDetail;
import entity.SuperEntity;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import org.modelmapper.ModelMapper;
import repository.custom.OrderDao;
import service.ServiceFactory;
import service.custom.OrderDetailService;
import util.CrudUtil;
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
    @Override
    public boolean save(OrderEntity order) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        String SQL="INSERT INTO Orders VALUES(?,?,?,?,?,?)";
        PreparedStatement pst=connection.prepareStatement(SQL);
        pst.setObject(1,order.getId());
        pst.setObject(2,order.getUserID());
        pst.setObject(3,order.getCustomerID());
        pst.setObject(4,order.getOrderDate());
        pst.setObject(5,order.getPaymentType());
        boolean isOrderAdd= pst.executeUpdate()>0;
        if(isOrderAdd){
            OrderDetailService type= ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAIL);
            if (type.addOrderDetail(order.getOrderDetails())) {
                List<OrderDetailEntity> orderDetailEntities=new ArrayList<>();
                order.getOrderDetails().forEach(orderDetail -> {
                    orderDetailEntities.add(new ModelMapper().map(orderDetail, OrderDetailEntity.class));
                });
                if (new ProductDaoImpl().updateStock(orderDetailEntities)) {

                    connection.commit();

                    return true;
                }
            }

        }
        connection.rollback();
        return false;
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

            ResultSet rst= CrudUtil.execute(SQL);
            while(rst.next()){
                orderEntities.add(new OrderEntity(rst.getString(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),(List<OrderDetail>)rst.getArray(6)));
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
                orderDetails.add((OrderDetail)rst.getArray(6));
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


}
