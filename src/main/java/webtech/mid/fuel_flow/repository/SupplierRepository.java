package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webtech.mid.fuel_flow.model.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
List<Supplier> findByCompanyNameIgnoreCaseOrderByContactNameAsc(String companyName);
}
