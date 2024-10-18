package repository.custom.impl;

import entity.SuperEntity;
import entity.OrderDetailEntity;
import repository.custom.OrderDetailDao;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetailEntity entity) {
        String SQL="INSERT INTO orderDetail Values(?,?,?,?) ";

        try {
            return CrudUtil.execute(SQL,
                    entity.getOrderID(),
                    entity.getProductID(),
                    entity.getQuantity(),
                    entity.getDiscount()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean update(OrderDetailEntity entity) {
        return false;
    }

    @Override
    public List<OrderDetailEntity> findAll() {
        return List.of();
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
