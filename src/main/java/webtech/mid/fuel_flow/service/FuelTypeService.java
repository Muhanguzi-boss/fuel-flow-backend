package webtech.mid.fuel_flow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.FuelType;
import webtech.mid.fuel_flow.repository.FuelTypeRepository;

@Service
public class FuelTypeService {

private final FuelTypeRepository fuelTypeRepository;

public FuelTypeService(FuelTypeRepository fuelTypeRepository){
    this.fuelTypeRepository = fuelTypeRepository;
}

    public String save(FuelType fuelType) {
        fuelTypeRepository.save(fuelType);
        return "Fuel type saved successfully";
    }

    public List<FuelType> getAll() {
        return fuelTypeRepository.findAll();
    }

    public List<FuelType> getUnused() {
    return fuelTypeRepository.findUnused();
}

    public Optional<FuelType> getById(UUID id) {
        return fuelTypeRepository.findById(id);
    }

    public String update(UUID id, FuelType fuelType) {
        Optional<FuelType> optional = fuelTypeRepository.findById(id);
        if (optional.isEmpty()) {
            return "The fuel type with that id does not exist";
        }
        FuelType existing = optional.get();
        existing.setName(fuelType.getName());
        fuelTypeRepository.save(existing);
        return "Fuel type updated successfully";
    }

    public String delete(UUID id) {
        if (!fuelTypeRepository.existsById(id)) {
            return "The fuel type with that id does not exist";
        }
        fuelTypeRepository.deleteById(id);
        return "Fuel type deleted successfully";
    }
}
