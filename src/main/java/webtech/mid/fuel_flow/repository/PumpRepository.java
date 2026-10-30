package webtech.mid.fuel_flow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Pump;
@Repository
public interface PumpRepository extends JpaRepository<Pump, UUID> {

}
