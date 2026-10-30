package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import webtech.mid.fuel_flow.model.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
List<Supplier> findByCompanyNameIgnoreCaseOrderByContactNameAsc(String companyName);
@Query("SELECT DISTINCT d.supplier FROM Delivery d WHERE d.attendant.id = :attendantId")
List<Supplier> findByAttendantId(@Param("attendantId") UUID attendantId);
}
