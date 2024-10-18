package controller.order;

import dto.OrderDetail;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public boolean addOrderDetail(List<OrderDetail> orderDetails) {
        for(OrderDetail orderDetail:orderDetails){
            if(!addOrderDetail(orderDetail)){
                return false;
            }
        }
        return true;
    }

    public boolean addOrderDetail(OrderDetail orderDetails){
        String SQL="INSERT INTO orderDetail Values(?,?,?,?) ";

        try {
            return CrudUtil.execute(SQL,
                orderDetails.getOrderID(),
                orderDetails.getProductID(),
                orderDetails.getQuantity(),
                orderDetails.getDiscount()
             );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
