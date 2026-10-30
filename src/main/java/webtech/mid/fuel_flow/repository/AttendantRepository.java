package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Attendant;
@Repository
public interface AttendantRepository extends JpaRepository<Attendant, UUID>{
@Query("SELECT DISTINCT a FROM Attendant a JOIN a.fuelTypes f WHERE LOWER(f.name) = LOWER(:name)")
List<Attendant> findByFuelTypeName(@Param("name") String name);
}
