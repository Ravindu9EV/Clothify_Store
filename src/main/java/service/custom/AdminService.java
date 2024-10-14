package service.custom;

import dto.Admin;
import service.SuperService;

public interface AdminService extends SuperService {
    boolean addAdmin(Admin admin);
    Admin searchAdmin(String id);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(String id);
    Admin getAdmin();

}
