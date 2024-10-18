package service.custom;

import dto.Admin;
import service.SuperService;

import java.util.List;

public interface AdminService extends SuperService {
    boolean addAdmin(Admin admin);
    Admin searchAdmin(String id);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(String id);
    List<Admin> getAdmin();

}
