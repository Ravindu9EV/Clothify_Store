package repository.custom;

import dto.Order;
import entity.OrderEntity;
import repository.CrudRepository;

public interface OrderDao extends CrudRepository<OrderEntity> {
    Order search(String id, String customerID);
}
