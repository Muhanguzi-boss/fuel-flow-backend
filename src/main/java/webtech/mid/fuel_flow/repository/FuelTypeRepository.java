package webtech.mid.fuel_flow.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.FuelType;
@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, UUID> {
@Query("SELECT f FROM FuelType f WHERE f.attendants IS EMPTY")
List<FuelType> findUnused();
}
