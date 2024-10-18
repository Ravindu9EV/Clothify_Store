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

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity order) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        String SQL="INSERT INTO orders VALUES(?,?,?,?,?,?)";
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
        return false;
    }

    @Override
    public List<OrderEntity> findAll() throws SQLException {
        String SQL="Select * FROM Order ";

        List<OrderEntity> orderEntities=new ArrayList<>();

            ResultSet rst= CrudUtil.execute(SQL);
            while(rst.next()){
                orderEntities.add(new OrderEntity(rst.getInt(1),rst.getString(2),rst.getString(3),LocalDate.parse(rst.getString(4)),rst.getString(5),(List<OrderDetail>)rst.getArray(6)));
            }

        return orderEntities;
    }

    @Override
    public SuperEntity search(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


}
