package service;

import service.custom.impl.AdminServiceImpl;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

import static util.DaoType.*;

public class ServiceFactory {
    public static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance==null ? instance=new ServiceFactory() : instance;
    }
    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case ADMIN:return (T) new AdminServiceImpl();
            case EMPLOYEE: return (T) new EmployeeServiceImpl();
            case SUPPLIER: return (T) new SupplierServiceImpl();
            case PRODUCT: return (T) ProductServiceImpl.getInstance();

        }
        return null;
    }
}
