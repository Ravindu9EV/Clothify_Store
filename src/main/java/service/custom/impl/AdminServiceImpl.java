package service.custom.impl;

import dto.Admin;
import entity.AdminEntity;
import repository.DaoFactory;
import repository.custom.AdminDao;
import service.custom.AdminService;
import util.DaoType;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao type= DaoFactory.getInstance().getDaoType(DaoType.ADMIN);

    @Override
    public boolean addAdmin(Admin admin) {

        if(admin!=null){
            try {
                return type.save(new ModelMapper().map(admin, AdminEntity.class));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return false;
        }
    }

    @Override
    public Admin searchAdmin(String id) {

        AdminEntity entity=(AdminEntity) type.search(id);
        System.out.println("helllooooo");
        return entity!=null? new ModelMapper().map(entity, Admin.class):null;


    }

    @Override
    public boolean updateAdmin(Admin admin) {

        if(admin!=null){
            return type.update(new ModelMapper().map(admin, AdminEntity.class));
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteAdmin(String id) {
        if(id.equals(null) || id==""){
            return false;
        }else{
            return type.delete(id);
        }
    }

    @Override
    public List<Admin> getAdmin() {

        try {
            List<Admin> admins=new ArrayList<>();
            for (AdminEntity  entity:type.findAll()) {
               if(entity!=null) admins.add(new ModelMapper().map(entity, Admin.class));
            }
            return admins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
