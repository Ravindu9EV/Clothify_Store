package service.custom.impl;

import dto.Customer;
import service.custom.CustomerService;
import util.CrudUtil;
import org.modelmapper.ModelMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {
    private static CustomerServiceImpl instance;

    private CustomerServiceImpl(){

    }
    public static CustomerServiceImpl getInstance(){
        return instance==null ? instance=new CustomerServiceImpl() : instance;
    }
    @Override
    public boolean addCustomer(Customer customer) {
        String SQL="INSERT INTO Customer VALUES(?,?,?,?)";
        return CrudUtil.execute(SQL,customer.getId(),customer.getName(),customer.getEmail(),customer.getCotact());

    }

    @Override
    public Customer searchCustomerByID(String id) {
        String SQL="SELECT * FROM Customer WHERE id='"+id+"'";
        ResultSet rst=CrudUtil.execute(SQL);
        try {
            while(rst.next()){
                return new ModelMapper().map( new Customer(rst.getString(1),rst.getString(2), rst.getString(3),rst.getString(4) ),Customer.class);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Customer searchCustomerByEmail(String email) {
        String SQL="SELECT * FROM Customer WHERE id='"+email+"'";
        ResultSet rst=CrudUtil.execute(SQL);
        try {
            while(rst.next()){
                return new ModelMapper().map( new Customer(rst.getString(1),rst.getString(2), rst.getString(3),rst.getString(4) ),Customer.class);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }
}
