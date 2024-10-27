package service.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierDao supplierDao= DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    @Override
    public boolean addSupplier(Supplier supplier) {
        if(supplier!=null){
            try {
                return supplierDao.save(new ModelMapper().map(supplier, SupplierEntity.class));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return false;
        }
    }

    @Override
    public Supplier searchSupplier(String id) {
        SupplierEntity entity=(SupplierEntity) supplierDao.search(id);
        if(entity!=null){
            return new ModelMapper().map(entity,Supplier.class);
        }
        return null;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        if(supplier!=null){
            return supplierDao.update(new ModelMapper().map(supplier,SupplierEntity.class));

        }
        return false;
    }

    @Override
    public boolean deleteSupplier(String id) {
        return id.isEmpty() ? false: supplierDao.delete(id);
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers=new ArrayList<>();
        try {
           for(SupplierEntity supplierEntity:supplierDao.findAll()){
               if(supplierEntity!=null){
                   suppliers.add(new ModelMapper().map(supplierEntity,Supplier.class));
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suppliers;
    }



}
