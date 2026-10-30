package webtech.mid.fuel_flow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.Attendant;
import webtech.mid.fuel_flow.repository.AttendantRepository;
import webtech.mid.fuel_flow.repository.FuelTypeRepository;

@Service
public class AttendantService {

    private final AttendantRepository attendantRepository;

    public AttendantService(AttendantRepository attendantRepository){
        this.attendantRepository = attendantRepository;
    }



    public String save(Attendant attendant) {
        attendantRepository.save(attendant);
        return "Attendant saved successfully";
    }

    public List<Attendant> getAll() {
        return attendantRepository.findAll();
    }

    public List<Attendant> getByFuelType(String name) {
    return attendantRepository.findByFuelTypeName(name);
}

public boolean existsById(UUID id) {
    return attendantRepository.existsById(id);
}

    public Optional<Attendant> getById(UUID id) {
        return attendantRepository.findById(id);
    }

    public String update(UUID id, Attendant attendant) {
        Optional<Attendant> optional = attendantRepository.findById(id);
        if (optional.isEmpty()) {
            return "The attendant with that id does not exist";
        }
        Attendant existing = optional.get();
        existing.setFirstName(attendant.getFirstName());
        existing.setLastName(attendant.getLastName());
        existing.setBadgeNumber(attendant.getBadgeNumber());
        existing.setPump(attendant.getPump());
        if (attendant.getFuelTypes() != null) {
            existing.setFuelTypes(attendant.getFuelTypes());
        }
        attendantRepository.save(existing);
        return "Attendant updated successfully";
    }

    public String delete(UUID id) {
        if (!attendantRepository.existsById(id)) {
            return "The attendant with that id does not exist";
        }
        attendantRepository.deleteById(id);
        return "Attendant deleted successfully";
    }
}