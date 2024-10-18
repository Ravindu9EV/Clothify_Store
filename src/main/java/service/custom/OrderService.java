package service.custom;

import dto.Customer;
import dto.Order;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface OrderService extends SuperService {
    boolean placeOrder(Order order) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
}
