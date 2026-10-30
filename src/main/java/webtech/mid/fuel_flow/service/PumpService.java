package webtech.mid.fuel_flow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.Pump;
import webtech.mid.fuel_flow.repository.PumpRepository;

@Service
public class PumpService {

    private final PumpRepository pumpRepository;

    public PumpService(PumpRepository pumpRepository){
        this.pumpRepository = pumpRepository;
    }

    public String save(Pump pump) {
        pumpRepository.save(pump);
        return "Pump saved successfully";
    }

    public List<Pump> getAll() {
        return pumpRepository.findAll();
    }

    public List<Pump> getFreePumps() {
    return pumpRepository.findFreePumps();
}

    public Optional<Pump> getById(UUID id) {
        return pumpRepository.findById(id);
    }

    public String update(UUID id, Pump pump) {
        Optional<Pump> optional = pumpRepository.findById(id);
        if (optional.isEmpty()) {
            return "The pump with that id does not exist";
        }
        Pump existing = optional.get();
        existing.setPumpNumber(pump.getPumpNumber());
        existing.setName(pump.getName());
        pumpRepository.save(existing);
        return "Pump updated successfully";
    }

    public String delete(UUID id) {
        if (!pumpRepository.existsById(id)) {
            return "The pump with that id does not exist";
        }
        pumpRepository.deleteById(id);
        return "Pump deleted successfully";
    }
}
