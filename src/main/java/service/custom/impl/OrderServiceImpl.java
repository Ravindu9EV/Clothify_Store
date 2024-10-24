package service.custom.impl;

import dto.Order;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.custom.OrderDetailService;
import service.custom.OrderService;
import service.custom.ProductService;
import util.DaoType;
import util.ServiceType;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAIL);
    OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    OrderDetailService orderDetailService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAIL);
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @Override
    public boolean placeOrder(Order order){

        try {
            return orderDao.save(new ModelMapper().map(order, OrderEntity.class));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public List<Order> getAllOrders()  {
        ObservableList<Order> observableList= FXCollections.observableArrayList();
        try {
            orderDao.findAll().forEach(orderEntity -> {
                if(orderEntity!=null) {
                    observableList.add(new ModelMapper().map(orderEntity, Order.class));
                }
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

    @Override
    public Order search(String id, String customerID) {
        Order entity= orderDao.search(id,customerID);
        return entity!=null ? entity : new ModelMapper().map(entity,Order.class);
    }
}



