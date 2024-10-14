package service.custom;

import dto.Admin;
import dto.Employee;
import dto.User;
import service.SuperService;
import util.DaoType;

public interface LoginService extends SuperService {
    DaoType checkAdminLogin(String email, String password);
    DaoType checkEmployeeLogin(String email, String password);
    User getUser();
}
