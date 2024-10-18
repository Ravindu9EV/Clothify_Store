package service.custom;

import dto.Employee;
import dto.OrderDetail;
import service.SuperService;

import java.util.List;

public interface OrderDetailService extends SuperService {
    boolean addOrderDetail(List<OrderDetail> orderDetails);
    boolean addOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getAll();
}
