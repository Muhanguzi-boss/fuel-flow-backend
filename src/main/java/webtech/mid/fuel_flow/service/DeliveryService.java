package webtech.mid.fuel_flow.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.model.DeliveryStatus;
import webtech.mid.fuel_flow.repository.DeliveryRepository;
import webtech.mid.fuel_flow.repository.TankOffloadRepository;

@Service
public class DeliveryService {

private final DeliveryRepository deliveryRepository;
private final TankOffloadRepository tankOffloadRepository;

public DeliveryService(DeliveryRepository deliveryRepository, TankOffloadRepository tankOffloadRepository){
    this.deliveryRepository = deliveryRepository;
    this.tankOffloadRepository = tankOffloadRepository;
}

  public String save(Delivery delivery) {
    if (delivery.getDeliveryDate().isBefore(LocalDate.now())) {
        return "Delivery date cannot be in the past";
    }
    if (isAttendantBooked(delivery)) {
        return "Attendant already receives a delivery on that date";
    }
    delivery.setId(null);
    delivery.setStatus(DeliveryStatus.ORDERED);
    deliveryRepository.save(delivery);
    return "Delivery saved successfully";
}

public boolean isAttendantBooked(Delivery delivery) {
    if (delivery.getAttendant() == null || delivery.getAttendant().getId() == null) {
        return false;
    }
    return deliveryRepository.existsByAttendantIdAndDeliveryDateAndStatusNot(
            delivery.getAttendant().getId(), delivery.getDeliveryDate(), DeliveryStatus.CANCELLED);
}

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public List<Delivery> getByStatus(DeliveryStatus status) {
    return deliveryRepository.findByStatusOrderByDeliveryDateAsc(status);
}

public List<Delivery> getBetween(LocalDate start, LocalDate end) {
    return deliveryRepository.findByDeliveryDateBetweenOrderByDeliveryDateAsc(start, end);
}

public List<Object[]> countByStatus() {
    return deliveryRepository.countByStatus();
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
        existing.setTruckPlate(delivery.getTruckPlate());
        existing.setOrderedLitres(delivery.getOrderedLitres());
        existing.setSupplier(delivery.getSupplier());
        existing.setAttendant(delivery.getAttendant());
        deliveryRepository.save(existing);
        return "Delivery updated successfully";
    }

    public String changeStatus(UUID id, DeliveryStatus newStatus) {
    Optional<Delivery> optional = deliveryRepository.findById(id);
    if (optional.isEmpty()) {
        return "The delivery with that id does not exist";
    }
    Delivery delivery = optional.get();
    DeliveryStatus current = delivery.getStatus();
    boolean allowed = false;
    if (current == DeliveryStatus.ORDERED) {
        allowed = newStatus == DeliveryStatus.DISPATCHED || newStatus == DeliveryStatus.CANCELLED;
    } else if (current == DeliveryStatus.DISPATCHED) {
        allowed = newStatus == DeliveryStatus.RECEIVED || newStatus == DeliveryStatus.CANCELLED;
    }
    if (!allowed) {
        return "Cannot change status from " + current + " to " + newStatus;
    }
    delivery.setStatus(newStatus);
    deliveryRepository.save(delivery);
    return "Delivery status updated successfully";
}

public String delete(UUID id) {
    Optional<Delivery> optional = deliveryRepository.findById(id);
    if (optional.isEmpty()) {
        return "The delivery with that id does not exist";
    }
    if (tankOffloadRepository.existsByDelivery(optional.get())) {
        return "That delivery has tank offloads, delete them first";
    }
    deliveryRepository.deleteById(id);
    return "Delivery deleted successfully";
}

@Transactional
public String cancelDay(UUID attendantId, LocalDate date) {
    int cancelled = deliveryRepository.cancelDay(attendantId, date);
    return cancelled + " deliveries cancelled";
}


}
