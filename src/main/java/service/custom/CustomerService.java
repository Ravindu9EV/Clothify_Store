package service.custom;

import dto.Customer;
import dto.Employee;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);
    Customer searchCustomerByID(String id);
    Customer searchCustomerByEmail(String email);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    List<Customer> getAll() throws SQLException;
}
