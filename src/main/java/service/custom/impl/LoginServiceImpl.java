package service.custom.impl;

import dto.Admin;

import dto.Employee;
import dto.User;
import service.custom.LoginService;
import util.CrudUtil;
import util.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {
    public static LoginServiceImpl instance;
    private LoginServiceImpl(){}

    private User user;
    private DaoType userType;
    public static LoginServiceImpl getInstance(){
        return instance==null ? instance=new LoginServiceImpl() : instance;
    }
    @Override
    public DaoType checkAdminLogin(String email, String password) {

        String SQL="SELECT * FROM Admin WHERE email='"+email+"' and password='"+password+"'";


        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while (rst.next()){
                user=  new Admin(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
                return  userType;
            }
        }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

    @Override
    public DaoType checkEmployeeLogin(String email, String password) {
        String SQL="SELECT * FROM Employee WHERE email='"+email+"' and password='"+password+"'";


        try {
            ResultSet rst=CrudUtil.execute(SQL);
            while (rst.next()){
                user=  new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
                return userType;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUserType(DaoType type){
        switch (type){
            case ADMIN: userType=DaoType.ADMIN ;
            case EMPLOYEE: userType=DaoType.EMPLOYEE;
        }

    }
    public DaoType getUserType(){
        return userType;
    }
}





