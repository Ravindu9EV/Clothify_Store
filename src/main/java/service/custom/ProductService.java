package service.custom;

import dto.OrderDetail;
import dto.Product;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    Product searchProduct(String id);
    boolean updateProduct(Product product);
    boolean deleteProduct(String id);
    List<Product> getAllProducts() throws SQLException;
    boolean updateStock(List<OrderDetail> orderDetails);
    boolean updateStock(OrderDetail orderDetail);
}
