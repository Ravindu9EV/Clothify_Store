package service;

import dto.OrderDetail;
import service.custom.impl.*;
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
            case PRODUCT: return (T) new ProductServiceImpl();
            case CUSTOMER: return (T) new CustomerServiceImpl();
            case ORDER: return (T) new OrderServiceImpl();
            case ORDERDETAIL: return (T) new OrderDetailServiceImpl();

        }
        return null;
    }
}
