package repository.custom.impl;

import dto.Product;
import entity.SuperEntity;
import entity.OrderDetailEntity;
import entity.ProductEntity;
import repository.custom.ProductDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
        String SQL="INSERT INTO Product VALUES(?,?,?,?,?,?,?)";
        try {
           return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getSize(),entity.getPrice(),entity.getQuantity(),entity.getCategory(),entity.getImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean update(ProductEntity entity) {
        String SQL="UPDATE Product SET name=?, size=?, price=?, quantity=?, category=?,image=? WHERE id='"+entity.getId()+"'";
        try {
            return CrudUtil.execute(SQL,entity.getName(),entity.getSize(),entity.getPrice(),entity.getQuantity(),entity.getCategory(),entity.getImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductEntity> findAll() {
        String SQL="Select * FROM product";
        List<ProductEntity> products=new ArrayList<>();


        try {
            ResultSet rst= CrudUtil.execute(SQL);

            while(rst.next()){
                ProductEntity entity=new ProductEntity(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getDouble(4),
                        rst.getInt(5),
                        rst.getString(6),
                        rst.getString(7)
                );
                products.add(entity);
            }
            return products;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductEntity search(String id) {
        String SQL="Select * from Product where id='"+id+"'";
        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while(rst.next()){
                return new ProductEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getInt(5),rst.getString(6),rst.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String SQL="Delete From Product Where id='"+id+"'";
        try {
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean updateStock(List<OrderDetailEntity> orderDetailEntities) {
        for(OrderDetailEntity orderDetail:orderDetailEntities){
            return updateStock(orderDetail);
        }
        return false;
    }

    @Override
    public boolean updateStock(OrderDetailEntity orderDetailEntity) {
        String SQL="UPDATE Product SET quantity=quantity-? WHERE id=?";
        try {
            return CrudUtil.execute(SQL,orderDetailEntity.getQuantity(),orderDetailEntity.getProductID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
