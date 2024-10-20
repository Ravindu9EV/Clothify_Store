package repository.custom;

import entity.OrderDetailEntity;
import repository.CrudRepository;

import java.util.List;

public interface OrderDetailDao extends CrudRepository<OrderDetailEntity> {
    List<OrderDetailEntity> findAll(String id);

}
