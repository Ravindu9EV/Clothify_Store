package service.custom.impl;

import dto.Order;
import dto.OrderDetail;
import dto.Product;
import entity.OrderDetailEntity;
import entity.ProductEntity;
import javafx.collections.FXCollections;
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

    ProductDao productDao=DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
    @Override
    public boolean addProduct(Product product) {


        try {
            return productDao.save(new ModelMapper().map(product, ProductEntity.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product searchProduct(String id) {
        return new ModelMapper().map(productDao.search(id),Product.class);
    }

    @Override
    public boolean updateProduct(Product product) {

        return productDao.update(new ModelMapper().map(product,ProductEntity.class));
    }

    @Override
    public boolean deleteProduct(String id) {
        return productDao.delete(id);
    }

    @Override
    public List<Product> getAllProducts()  {
        List<Product> products= new ArrayList<>();
        try {
            productDao.findAll().forEach(productEntity -> {
                products.add(new ModelMapper().map(productEntity,Product.class));
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;



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
       ProductDao type= DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
       return type.updateStock(new ModelMapper().map(orderDetail, OrderDetailEntity.class));

    }


}
