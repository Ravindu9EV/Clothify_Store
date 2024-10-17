package service.custom;

import dto.Customer;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    Customer searchCustomerByID(String id);
    Customer searchCustomerByEmail(String email);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
}
