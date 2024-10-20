package repository.custom;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import entity.ProductEntity;
import entity.SuperEntity;
import repository.CrudRepository;

import java.util.List;


public interface ProductDao extends CrudRepository<ProductEntity> {
    boolean updateStock(List<OrderDetailEntity> orderDetailEntities);
    boolean updateStock(OrderDetailEntity orderDetailEntity);
    SuperEntity search(String id);

}
