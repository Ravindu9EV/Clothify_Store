package service.custom.impl;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import repository.DaoFactory;
import repository.custom.OrderDetailDao;
import service.custom.OrderDetailService;
import org.modelmapper.ModelMapper;
import util.DaoType;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailDao orderDetailDao=DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAIL);
    @Override
    public boolean addOrderDetail(List<OrderDetail> orderDetails) {
        for(OrderDetail orderDetail:orderDetails){
            if(!addOrderDetail(orderDetail)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addOrderDetail(OrderDetail orderDetails){
//        String SQL="INSERT INTO orderDetail Values(?,?,?,?) ";
//
//        return CrudUtil.execute(SQL,
//                orderDetails.getOrderID(),
//                orderDetails.getProductID(),
//                orderDetails.getQuantity(),
//                orderDetails.getDiscount()
//        );
        try {
            return orderDetailDao.save(new ModelMapper().map(orderDetails, OrderDetailEntity.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<OrderDetail> getAll() {
        return List.of();
    }
}
