package webtech.mid.fuel_flow.service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.model.DeliveryStatus;
import webtech.mid.fuel_flow.model.TankOffload;
import webtech.mid.fuel_flow.repository.DeliveryRepository;
import webtech.mid.fuel_flow.repository.TankOffloadRepository;

@Service
public class TankOffloadService {

    private final TankOffloadRepository tankOffloadRepository;
    private final DeliveryRepository deliveryRepository;

    public TankOffloadService(TankOffloadRepository tankOffloadRepository, DeliveryRepository deliveryRepository) {
        this.tankOffloadRepository = tankOffloadRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public String save(UUID deliveryId, TankOffload tankOffload) {
        Optional<Delivery> optional = deliveryRepository.findById(deliveryId);
        if (optional.isEmpty()) {
            return "The delivery with that id does not exist";
        }
        Delivery delivery = optional.get();
        if (delivery.getStatus() != DeliveryStatus.RECEIVED) {
            return "Tank offloads are only for RECEIVED deliveries";
        }
        tankOffload.setId(null);
        tankOffload.setDelivery(delivery);
        tankOffload.setOffloadDate(LocalDate.now());
        tankOffloadRepository.save(tankOffload);
        return "Tank offload saved successfully";
    }

    public List<TankOffload> getAll() {
        return tankOffloadRepository.findAll();
    }

    public List<TankOffload> getBySupplierCode(String supplierCode) {
        return tankOffloadRepository.findBySupplierCode(supplierCode);
    }

    public String delete(UUID id) {
        if (!tankOffloadRepository.existsById(id)) {
            return "The tank offload with that id does not exist";
        }
        tankOffloadRepository.deleteById(id);
        return "Tank offload deleted successfully";
    }

}
