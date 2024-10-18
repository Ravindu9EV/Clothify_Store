package service.custom.impl;

import dto.Customer;
import entity.CustomerEntity;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custom.CustomerService;
import util.CrudUtil;
import org.modelmapper.ModelMapper;
import util.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao= DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
    @Override
    public boolean addCustomer(Customer customer) {
        if(customer!=null){
            try {
                return customerDao.save(new ModelMapper().map(customer, CustomerEntity.class));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return false;
        }

    }

    @Override
    public Customer searchCustomerByID(String id) {
       if(id==null ^ id.length()<=0){
           return null;
       }else{
           String SQL="Select * FROM Customer Where id='"+id+"'";
           return new ModelMapper().map(customerDao.search(SQL), Customer.class);
       }
    }

    @Override
    public Customer searchCustomerByEmail(String email) {

        String SQL="SELECT * FROM Customer WHERE email='"+email+"'";
        CustomerEntity entity=(CustomerEntity) customerDao.search(SQL);
        return entity!=null ? new ModelMapper().map(entity, Customer.class): null;

    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDao.update(new ModelMapper().map(customer,CustomerEntity.class));
    }

    @Override
    public boolean deleteCustomer(String id) {
        return  customerDao.delete(id);
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers=new ArrayList<>();
        try {
            customerDao.findAll().forEach(customerEntity -> {
                customers.add(new ModelMapper().map(customerEntity,Customer.class));
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
