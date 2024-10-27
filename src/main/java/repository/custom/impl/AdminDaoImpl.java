package repository.custom.impl;

import entity.AdminEntity;
import entity.SuperEntity;
import org.hibernate.Session;
import repository.custom.AdminDao;
import util.CrudUtil;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity entity) {

        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(AdminEntity entity) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);

        session.getTransaction().commit();
        session.close();
        return true;
//         String SQL="UPDATE Admin SET name=?, contact=?, email=?, password=? WHERE id='"+entity.getId()+"'";
//        try {
//            return CrudUtil.execute(SQL,entity.getName(),entity.getContact(),entity.getEmail(),entity.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<AdminEntity> findAll() {
        String SQL="";
        List<AdminEntity>  adminEntities;
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        adminEntities=session.createQuery("SELECT admin FROM AdminEntity admin",AdminEntity.class).getResultList();
       // session.getTransaction().commit();
        session.close();
        return adminEntities!=null ? adminEntities : null;
    }

    @Override
    public SuperEntity search(String id) {
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
        AdminEntity adminEntity=session.find(AdminEntity.class,id);
        session.getTransaction().commit();
        session.close();
        return adminEntity!=null ? adminEntity : null;
//        String SQL="Select * FROM Admin Where id='"+id+"'";
//
//        try {
//            ResultSet rst=CrudUtil.execute(SQL);
//            while(rst.next()){
//                return new AdminEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
//            }
//
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
//        String SQL="Delete From Admin Where id='"+id+"'";
//        try {
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
