package service.custom;

import dto.Product;
import service.SuperService;

public interface ProductService extends SuperService {
    void addProduct(Product product);
    boolean searchProduct(String id);
    boolean updateProduct(Product product);
    void deleteProduct(String id);
}
