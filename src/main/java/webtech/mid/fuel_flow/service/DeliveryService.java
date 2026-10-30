package webtech.mid.fuel_flow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.repository.DeliveryRepository;

@Service
public class DeliveryService {

private final DeliveryRepository deliveryRepository;

public DeliveryService(DeliveryRepository deliveryRepository){
    this.deliveryRepository = deliveryRepository;
}

    public String save(Delivery delivery) {
        deliveryRepository.save(delivery);
        return "Delivery saved successfully";
    }

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> getById(UUID id) {
        return deliveryRepository.findById(id);
    }

    public String update(UUID id, Delivery delivery) {
        Optional<Delivery> optional = deliveryRepository.findById(id);
        if (optional.isEmpty()) {
            return "The delivery with that id does not exist";
        }
        Delivery existing = optional.get();
        existing.setDeliveryDate(delivery.getDeliveryDate());
        existing.setStatus(delivery.getStatus());
        existing.setTruckPlate(delivery.getTruckPlate());
        existing.setOrderedLitres(delivery.getOrderedLitres());
        existing.setSupplier(delivery.getSupplier());
        existing.setAttendant(delivery.getAttendant());
        deliveryRepository.save(existing);
        return "Delivery updated successfully";
    }

    public String delete(UUID id) {
        if (!deliveryRepository.existsById(id)) {
            return "The delivery with that id does not exist";
        }
        deliveryRepository.deleteById(id);
        return "Delivery deleted successfully";
    }
}
