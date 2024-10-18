package controller.order;

import db.DBConnection;
import dto.Customer;
import dto.Order;
import dto.OrderDetail;
import entity.OrderDetailEntity;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import repository.custom.impl.ProductDaoImpl;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ProductServiceImpl;
import util.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    public boolean placeOrder(Order order,Customer customer) throws SQLException {

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
               if (new OrderDetailController().addOrderDetail(order.getOrderDetails())) {
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
}
