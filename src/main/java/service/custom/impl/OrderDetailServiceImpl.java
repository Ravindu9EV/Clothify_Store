package service.custom.impl;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import repository.DaoFactory;
import repository.custom.OrderDetailDao;
import service.custom.OrderDetailService;
import org.modelmapper.ModelMapper;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public boolean addOrderDetail(OrderDetail orderDetail){
        System.out.println(orderDetail!=null? "[][]"+orderDetail: "No Detail");
        try {
            return orderDetail!=null ? orderDetailDao.save(new ModelMapper().map(orderDetail, OrderDetailEntity.class)) :false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetail> getAll() {
        List<OrderDetail> orderDetails=new ArrayList<>();
       try{

           for(OrderDetailEntity orderDetailEntity:orderDetailDao.findAll()){
               if(orderDetailEntity!=null){
                   orderDetails.add(new ModelMapper().map(orderDetailEntity,OrderDetail.class));
                   System.out.println("\n\nadded"+orderDetailEntity);
               }
           }
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
       return orderDetails;
    }

}
