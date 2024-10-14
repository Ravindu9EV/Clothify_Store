package service.custom;

import dto.Supplier;
import service.SuperService;

public interface SupplierService extends SuperService {
    void addSupplier(Supplier supplier);
    boolean searchSupplier(String id);
    boolean updateSupplier(Supplier supplier);
    void deleteSupplier(String id);
}
