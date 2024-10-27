package service.custom;

import dto.Employee;
import dto.OrderDetail;
import entity.OrderDetailEntity;
import service.SuperService;

import java.util.List;

public interface OrderDetailService extends SuperService {
    boolean addOrderDetail(List<OrderDetailEntity> orderDetails);
    boolean addOrderDetail(OrderDetailEntity orderDetailEntity);
    List<OrderDetail> getAll();
}
