package repository.custom.impl;

import entity.AdminEntity;
import entity.EmployeeEntity;
import entity.SuperEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import repository.custom.EmployeeDao;
import util.CrudUtil;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            session.close();
            return true;


//        String SQL="INSERT INTO Employee VALUES(?,?,?,?,?,?)";
//        try {
//            return CrudUtil.execute(SQL,entity.getId(),entity.getName(),entity.getContact(),entity.getCompany(),entity.getEmail(),entity.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public boolean update(EmployeeEntity entity) {
            Session session=HibernateUtil.getSession();
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return true;
//        String SQL="UPDATE Employee SET name=?, contact=?, company=?, email=?, password=? WHERE id='"+entity.getId()+"'";
//        try {
//            return CrudUtil.execute(SQL,entity.getName(),entity.getContact(),entity.getCompany(),entity.getEmail(),entity.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<EmployeeEntity> findAll() {

        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        List<EmployeeEntity> employeeEntities=session.createQuery("SELECT employee From EmployeeEntity employee").getResultList();
        session.getTransaction().commit();
        session.close();

//        String SQL="SELECT * FROM Employee";
//        List<EmployeeEntity>  employeeEntities=new ArrayList<>();
//        try {
//            ResultSet rst= CrudUtil.execute(SQL);
//            while (rst.next()){
//                employeeEntities.add(new EmployeeEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return employeeEntities;
    }

    @Override
    public SuperEntity search(String id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        EmployeeEntity entity=session.find(EmployeeEntity.class,id);
        session.getTransaction().commit();
        session.close();
        return entity;
//        String SQL="Select * FROM Employee Where id='"+id+"'";
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while (rst.next()){
//                return new EmployeeEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
//
//            }
//           } catch (SQLException e) {
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
//        String SQL="Delete From Employee Where id='"+id+"'";
//        try {
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

}

