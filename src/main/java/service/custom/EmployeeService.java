package service.custom;

import dto.Employee;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    boolean addEmployee(Employee employee);
    Employee searchEmployee(String id);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(String id);
    List<Employee> getAll();
}
