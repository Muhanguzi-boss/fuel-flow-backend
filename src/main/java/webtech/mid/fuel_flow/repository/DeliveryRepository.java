package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.model.DeliveryStatus;
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
List<Delivery> findByStatusOrderByDeliveryDateAsc(DeliveryStatus status);
List<Delivery> findByDeliveryDateBetweenOrderByDeliveryDateAsc(LocalDate start, LocalDate end);
boolean existsByAttendantIdAndDeliveryDateAndStatusNot(UUID attendantId, LocalDate deliveryDate, DeliveryStatus status);
}
