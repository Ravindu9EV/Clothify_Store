package service.custom.impl;

import dto.Order;
import dto.OrderDetail;
import dto.Product;
import entity.ProductEntity;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import repository.DaoFactory;
import org.modelmapper.ModelMapper;
import repository.custom.ProductDao;
import service.custom.ProductService;
import util.CrudUtil;
import util.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;

    private ProductServiceImpl(){}

    public static ProductServiceImpl getInstance(){
        return instance==null ? instance=new ProductServiceImpl() :instance;
    }

    @Override
    public boolean addProduct(Product product) {
        ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

        return productDao.save(new ModelMapper().map(product, ProductEntity.class));
    }

    @Override
    public boolean searchProduct(String id) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public List<Product> getAllIProducts() {
        String SQL="Select * FROM Product";
        List<Product> products=new ArrayList<>();
        ResultSet rst= CrudUtil.execute(SQL);

        try {
            while(rst.next()){
                    products.add(new ModelMapper().map(new ProductEntity(
                            rst.getInt(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getDouble(4),
                            rst.getInt(5),
                            rst.getString(6),
                            rst.getString(7)
                    ), Product.class));
            }
            return products;
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }

    }

    public boolean updateStock(List<OrderDetail> orderDetails) {
        for(OrderDetail orderDetail:orderDetails){
            if(!updateStock(orderDetail)) {
                return false;
            }

        }
        return true;

    }

    @Override
    public boolean updateStock(OrderDetail orderDetail) {
        String SQL="UPDATE Product SET quantity=quantity-? WHERE id=?";
        return CrudUtil.execute(SQL,orderDetail.getQuantity(),orderDetail.getProductID());
    }


}
