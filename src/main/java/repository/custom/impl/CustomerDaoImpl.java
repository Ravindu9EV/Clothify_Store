package repository.custom.impl;

import entity.AdminEntity;
import entity.CustomerEntity;
import entity.SuperEntity;
import org.hibernate.Session;
import repository.custom.CustomerDao;
import util.CrudUtil;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
   //private Session session = HibernateUtil.getSession();
    @Override
    public boolean save(CustomerEntity entity) {

        if(entity!=null){
            Session session= HibernateUtil.getSession();
            session.getTransaction().begin();
            session.persist( entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }
//        String SQL="INSERT INTO Customer VALUES(?,?,?,?)";
//        try {
//            return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getEmail(),entity.getContact());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }

    @Override
    public boolean update(CustomerEntity entity) {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
//        String SQL="UPDATE Customer SET CustomerName=?, CustomerEmail=?, CustomerContact=? WHERE id='"+entity.getId()+"'";
//        try {
//            return CrudUtil.execute(SQL,entity.getName(),entity.getEmail(),entity.getContact());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<CustomerEntity> findAll() {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        List<CustomerEntity> customerEntities=session.createQuery("SELECT c FROM CustomerEntity c").getResultList();
        session.getTransaction().commit();
        session.close();
//        String SQL="SELECT * FROM Customer";
//        List<CustomerEntity>  customerEntities=new ArrayList<>();
//        try {
//            ResultSet rst= CrudUtil.execute(SQL);
//            while (rst.next()){
//                customerEntities.add(new CustomerEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
//            }
//            System.out.println(customerEntities);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return customerEntities;
    }

    @Override
    public SuperEntity search(String id) {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        CustomerEntity customerEntity=session.find(CustomerEntity.class,id);
        session.getTransaction().commit();
        session.close();
        return customerEntity;
       /// String SQL="Select * FROM Customer Where id='"+id+"'";
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while ((rst.next())){
//                return new CustomerEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
//
//            }
//          } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
    }

    @Override
    public boolean delete(String id) {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        session.remove(search(id));
        session.getTransaction().commit();
        session.close();
        return true;
//        String SQL="Delete From Customer Where id='"+id+"'";
//        try {
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
