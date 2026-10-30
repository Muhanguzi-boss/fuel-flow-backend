package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Pump;
@Repository
public interface PumpRepository extends JpaRepository<Pump, UUID> {
@Query("SELECT p FROM Pump p WHERE NOT EXISTS (SELECT a FROM Attendant a WHERE a.pump = p) ORDER BY p.pumpNumber")
List<Pump> findFreePumps();
Optional<Pump> findByPumpNumber(int pumpNumber);
}
