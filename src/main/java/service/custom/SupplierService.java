package service.custom;

import dto.Employee;
import dto.Product;
import dto.Supplier;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    Supplier searchSupplier(String id);
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
    List<Supplier> getAll();
}
