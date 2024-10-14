package controller.login;

import service.custom.impl.LoginServiceImpl;
import util.DaoType;

public class LoginController {

    private static LoginController instance;
    private DaoType userType;
    private LoginController(){

    }
    public DaoType checkLogin(String email,String password){
        if(userType==DaoType.ADMIN  ){
            return  LoginServiceImpl.getInstance().checkAdminLogin(email,password);
        }else if(userType==DaoType.EMPLOYEE){
            return LoginServiceImpl.getInstance().checkEmployeeLogin(email,password);
        }
            return null;
    }

    public static LoginController getInstance(){
        return instance==null ? instance=new LoginController() : instance;
    }
    public void setUserType(DaoType userType){
        LoginServiceImpl.getInstance().setUserType(userType);
        this.userType=LoginServiceImpl.getInstance().getUserType();
    }
    public DaoType getUserType(){
        return this.userType;
    }



}
