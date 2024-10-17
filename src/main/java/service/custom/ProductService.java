package service.custom;

import dto.OrderDetail;
import dto.Product;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    boolean searchProduct(String id);
    boolean updateProduct(Product product);
    void deleteProduct(String id);
    List<Product> getAllIProducts();
    boolean updateStock(List<OrderDetail> orderDetails);
    boolean updateStock(OrderDetail orderDetail);
}
