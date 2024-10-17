package service.custom;

import dto.User;
import service.SuperService;

import util.ServiceType;

public interface LoginService extends SuperService {

    ServiceType checkUserLogin(String email, String password);
    User getUser();
    ServiceType getUserType();
    User findByEmail(String email);
}
