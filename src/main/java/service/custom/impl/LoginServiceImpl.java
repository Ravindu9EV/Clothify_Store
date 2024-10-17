package service.custom.impl;

import dto.Admin;

import dto.Employee;
import dto.User;
import service.custom.LoginService;
import util.CrudUtil;
import util.DaoType;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {
    public static LoginServiceImpl instance;
    private LoginServiceImpl(){}

    private User user;
    private ServiceType userType;
    public static LoginServiceImpl getInstance(){
        return instance==null ? instance=new LoginServiceImpl() : instance;
    }


    @Override
    public ServiceType checkUserLogin(String email, String password) {

        if(getUserType()==ServiceType.ADMIN){
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

        }else if(getUserType()==ServiceType.EMPLOYEE){
            String SQL="SELECT * FROM Employee WHERE email='"+email+"' and password='"+password+"'";
            try {
                ResultSet rst=CrudUtil.execute(SQL);
                while (rst.next()){
                    user=  new Admin(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
                    return  userType;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


        return null;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUserType(ServiceType type){
        switch (type){
            case ADMIN:
                userType=ServiceType.ADMIN;
                break ;
            case EMPLOYEE:
                userType=ServiceType.EMPLOYEE;
                break;
        }

    }
    public ServiceType getUserType(){
        return userType;
    }

    public User findByEmail(String email) {
        User user=null;
        try{
            ResultSet rst= CrudUtil.execute("Select * from Admin Where email='"+email+"'");

                while(rst.next()){
                    user= new Admin(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
                }
                if(user!=null){
                    rst=CrudUtil.execute("Select * FROM Employee WHERE email='"+email+"'");
                    while(rst.next()){
                        user=new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));

                    }
                }
                return user;

        }catch (SQLException e){
            throw new RuntimeException( e);
        }

    }
}





