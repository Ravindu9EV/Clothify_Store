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

        return new ModelMapper().map( supplierDao.search(id),Supplier.class);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {

        return supplierDao.update(new ModelMapper().map(supplier,SupplierEntity.class));
    }

    @Override
    public boolean deleteSupplier(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers=new ArrayList<>();
        try {
            supplierDao.findAll().forEach(supplierEntity -> {
                suppliers.add(new ModelMapper().map(supplierEntity,Supplier.class));
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suppliers;
    }
}
