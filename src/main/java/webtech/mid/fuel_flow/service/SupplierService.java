package webtech.mid.fuel_flow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.Supplier;
import webtech.mid.fuel_flow.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public String save(Supplier supplier) {
        supplierRepository.save(supplier);
        return "Supplier saved successfully";
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public List<Supplier> getByCompanyName(String companyName) {
    return supplierRepository.findByCompanyNameIgnoreCaseOrderByContactNameAsc(companyName);
}

public List<Supplier> getByAttendant(UUID attendantId) {
    return supplierRepository.findByAttendantId(attendantId);
}

    public Optional<Supplier> getById(UUID id) {
        return supplierRepository.findById(id);
    }

    public String update(UUID id, Supplier supplier) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) {
            return "The supplier with that id does not exist";
        }
        Supplier existing = optional.get();
        existing.setCompanyName(supplier.getCompanyName());
        existing.setContactName(supplier.getContactName());
        existing.setSupplierCode(supplier.getSupplierCode());
        existing.setPhone(supplier.getPhone());
        supplierRepository.save(existing);
        return "Supplier updated successfully";
    }

    public String delete(UUID id) {
        if (!supplierRepository.existsById(id)) {
            return "The supplier with that id does not exist";
        }
        supplierRepository.deleteById(id);
        return "Supplier deleted successfully";
    }
}
