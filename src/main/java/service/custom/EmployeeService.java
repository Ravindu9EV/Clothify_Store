package service.custom;

import dto.Employee;
import service.SuperService;

public interface EmployeeService extends SuperService {
    void addEmployee(Employee employee);
    boolean searchEmployee(String id);
    boolean updateEmployee(Employee employee);
    void deleteEmployee(String id);

}
