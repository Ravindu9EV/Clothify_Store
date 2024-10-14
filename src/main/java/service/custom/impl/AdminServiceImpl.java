package service.custom.impl;

import dto.Admin;
import service.custom.AdminService;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean addAdmin(Admin admin) {
        return false;
    }

    @Override
    public Admin searchAdmin(String id) {
        return null;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return false;
    }

    @Override
    public boolean deleteAdmin(String id) {
        return false;
    }

    @Override
    public Admin getAdmin() {
        return null;
    }
}
