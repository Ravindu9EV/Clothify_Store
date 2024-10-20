package service.custom.impl;

import controller.order.OrderDetailController;
import db.DBConnection;
import dto.Customer;
import dto.Order;
import dto.OrderDetail;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.custom.OrderDetailService;
import service.custom.OrderService;
import util.DaoType;
import org.modelmapper.ModelMapper;
import util.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDetailDao type=DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAIL);
    OrderDao orderDao=DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    @Override
    public boolean placeOrder(Order order)  {

        try {
            return orderDao.save(new ModelMapper().map(order, OrderEntity.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Order> getAllOrders()  {
        ObservableList<Order> observableList=FXCollections.observableArrayList();
        try {
            orderDao.findAll().forEach(orderEntity -> {
                observableList.add(new ModelMapper().map(orderEntity,Order.class));
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return observableList;
    }

    @Override
    public Order search(String id) {
       OrderEntity entity=(OrderEntity) orderDao.search(id);
        return entity==null ? null : new ModelMapper().map(entity,Order.class);
    }
}
