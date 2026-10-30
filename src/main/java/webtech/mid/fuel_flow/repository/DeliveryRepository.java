package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.model.DeliveryStatus;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
List<Delivery> findByStatusOrderByDeliveryDateAsc(DeliveryStatus status);
List<Delivery> findByDeliveryDateBetweenOrderByDeliveryDateAsc(LocalDate start, LocalDate end);
boolean existsByAttendantIdAndDeliveryDateAndStatusNot(UUID attendantId, LocalDate deliveryDate, DeliveryStatus status);
@Query("SELECT d.status, COUNT(d) FROM Delivery d GROUP BY d.status")
List<Object[]> countByStatus();

@Modifying
@Query("UPDATE Delivery d SET d.status = webtech.mid.fuel_flow.model.DeliveryStatus.CANCELLED "
        + "WHERE d.attendant.id = :attendantId AND d.deliveryDate = :date "
        + "AND d.status <> webtech.mid.fuel_flow.model.DeliveryStatus.RECEIVED")
int cancelDay(@Param("attendantId") UUID attendantId, @Param("date") LocalDate date);

long countByDeliveryDate(LocalDate deliveryDate);

long countDeliveriesByStatus(DeliveryStatus status);
}
