package webtech.mid.fuel_flow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Delivery;
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

}
