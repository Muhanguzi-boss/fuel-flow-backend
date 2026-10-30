package webtech.mid.fuel_flow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webtech.mid.fuel_flow.model.Attendant;
@Repository
public interface AttendantRepository extends JpaRepository<Attendant, UUID>{

}
