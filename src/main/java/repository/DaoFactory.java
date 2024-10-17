package repository;

import dto.Admin;
import dto.Employee;
import dto.Product;
import dto.Supplier;
import repository.custom.impl.AdminDaoImpl;
import repository.custom.impl.EmployeeDaoImpl;
import repository.custom.impl.ProductDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return instance==null ? instance=new DaoFactory() : instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type){
        switch (type){
            case ADMIN: return (T) new AdminDaoImpl();
            case EMPLOYEE: return (T) new EmployeeDaoImpl();
            case SUPPLIER: return (T) new SupplierDaoImpl();
            case PRODUCT: return (T) new ProductDaoImpl();
        }
        return null;
    }
}