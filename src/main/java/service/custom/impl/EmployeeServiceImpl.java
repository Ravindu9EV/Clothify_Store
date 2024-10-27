package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao type= DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
    @Override
    public boolean addEmployee(Employee employee) {

        if(employee!=null){
            try {
                return type.save(new ModelMapper().map(employee, EmployeeEntity.class));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            return false;
        }

    }

    @Override
    public Employee searchEmployee(String id) {
       EmployeeEntity entity=(EmployeeEntity) type.search(id);
      return  entity==null ? null : new ModelMapper().map(entity,Employee.class);
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        return employee!=null? type.update(new ModelMapper().map(employee, EmployeeEntity.class)):false;
    }

    @Override
    public boolean deleteEmployee(String id) {
        return  type.delete(id);
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees=new ArrayList<>();
        try {
            type.findAll().forEach(employeeEntity -> {
                employees.add(new ModelMapper().map(employeeEntity,Employee.class));
            });
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
