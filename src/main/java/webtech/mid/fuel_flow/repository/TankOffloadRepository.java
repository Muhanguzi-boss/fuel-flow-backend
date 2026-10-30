package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.model.TankOffload;

@Repository
public interface TankOffloadRepository extends JpaRepository<TankOffload, UUID> {
    @Query("SELECT t FROM TankOffload t WHERE t.delivery.supplier.supplierCode = :supplierCode ORDER BY t.offloadDate DESC")
    List<TankOffload> findBySupplierCode(@Param("supplierCode") String supplierCode);
    boolean existsByDelivery(Delivery delivery);
}
