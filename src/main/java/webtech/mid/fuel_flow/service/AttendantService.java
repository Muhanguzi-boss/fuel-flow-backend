package webtech.mid.fuel_flow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webtech.mid.fuel_flow.model.Attendant;
import webtech.mid.fuel_flow.model.FuelType;
import webtech.mid.fuel_flow.model.Pump;
import webtech.mid.fuel_flow.repository.AttendantRepository;
import webtech.mid.fuel_flow.repository.FuelTypeRepository;
import webtech.mid.fuel_flow.repository.PumpRepository;

@Service
public class AttendantService {

    private final AttendantRepository attendantRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final PumpRepository pumpRepository;

    public AttendantService(AttendantRepository attendantRepository, FuelTypeRepository fuelTypeRepository, PumpRepository pumpRepository){
        this.attendantRepository = attendantRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.pumpRepository = pumpRepository;
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

    @Transactional
public String addFuelType(UUID attendantId, UUID fuelTypeId) {
    Optional<Attendant> foundAttendant = attendantRepository.findById(attendantId);
    if (foundAttendant.isEmpty()) {
        return "The attendant with that id does not exist";
    }
    Optional<FuelType> foundFuelType = fuelTypeRepository.findById(fuelTypeId);
    if (foundFuelType.isEmpty()) {
        return "That fuel type does not exist";
    }
    Attendant attendant = foundAttendant.get();
    FuelType fuelType = foundFuelType.get();
    boolean alreadyHas = false;
    for (FuelType held : attendant.getFuelTypes()) {
        if (held.getId().equals(fuelType.getId())) {
            alreadyHas = true;
        }
    }
    if (alreadyHas) {
        return "The attendant already has that fuel type";
    }
    attendant.getFuelTypes().add(fuelType);
    attendantRepository.save(attendant);
    return "Fuel type added to the attendant";
}

public String changePump(UUID attendantId, int pumpNumber) {
    Optional<Attendant> foundAttendant = attendantRepository.findById(attendantId);
    if (foundAttendant.isEmpty()) {
        return "The attendant with that id does not exist";
    }
    Optional<Pump> foundPump = pumpRepository.findByPumpNumber(pumpNumber);
    if (foundPump.isEmpty()) {
        return "The pump with that pumpNumber does not exist";
    }
    Attendant attendant = foundAttendant.get();
    Pump pump = foundPump.get();
    boolean alreadyHisPump = attendant.getPump() != null
            && attendant.getPump().getId().equals(pump.getId());
    if (!alreadyHisPump && attendantRepository.existsByPump(pump)) {
        return "That pump is taken by another attendant";
    }
    attendant.setPump(pump);
    attendantRepository.save(attendant);
    return "Pump changed successfully";
}

}