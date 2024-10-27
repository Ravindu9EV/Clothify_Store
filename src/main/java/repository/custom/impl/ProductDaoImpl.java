package repository.custom.impl;

import dto.Product;
import entity.SuperEntity;
import entity.OrderDetailEntity;
import entity.ProductEntity;
import org.hibernate.Session;
import repository.custom.ProductDao;
import util.CrudUtil;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
//        String SQL="INSERT INTO Product VALUES(?,?,?,?,?,?,?)";
//        try {
//           return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getSize(),entity.getPrice(),entity.getQuantity(),entity.getCategory(),entity.getImage());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }


    @Override
    public boolean update(ProductEntity entity) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<ProductEntity> findAll() {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        List<ProductEntity> productEntities=session.createQuery("SELECT p FROM ProductEntity p").getResultList();
        session.getTransaction().commit();
        session.close();
        return productEntities;
//        String SQL="Select * FROM product";
//        List<ProductEntity> products=new ArrayList<>();
//
//
//        try {
//            ResultSet rst= CrudUtil.execute(SQL);
//
//            while(rst.next()){
//                ProductEntity entity=new ProductEntity(
//                        rst.getString(1),
//                        rst.getString(2),
//                        rst.getString(3),
//                        rst.getDouble(4),
//                        rst.getInt(5),
//                        rst.getString(6),
//                        rst.getString(7)
//                );
//                products.add(entity);
//            }
//            return products;
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public ProductEntity search(String id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        ProductEntity entity=session.find(ProductEntity.class,id);
        session.getTransaction().commit();
        session.close();
        return entity;
//        String SQL="Select * from Product where id='"+id+"'";
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while(rst.next()){
//                return new ProductEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getInt(5),rst.getString(6),rst.getString(7));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
    }

    @Override
    public boolean delete(String id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.remove(search(id));
        session.getTransaction().commit();
        session.close();
        return true;
//        String SQL="Delete From Product Where id='"+id+"'";
//        try {
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        String hql="UPDATE ProductEntity p SET p.quantity=p.quantity-:getQuantity WHERE p.id=:newID";
        session.createQuery(hql).setParameter("getQuantity",orderDetailEntity.getQuantity())
                        .setParameter("newID",orderDetailEntity.getProductID()).executeUpdate();

        session.getTransaction().commit();
        session.close();
        return true;
//        String SQL="UPDATE Product SET quantity=quantity-? WHERE id='"+orderDetailEntity.getProductID()+"'";
//        try {
//            boolean stu= CrudUtil.execute(SQL,orderDetailEntity.getQuantity());
//            System.out.println("productDao->updateStock-status: "+stu);
//            return stu;
//        } catch (SQLException e) {
//            System.out.println("productDao->updateStock( orderDetailEntity)"+e);
//            throw new RuntimeException(e);
//        }
    }
}
