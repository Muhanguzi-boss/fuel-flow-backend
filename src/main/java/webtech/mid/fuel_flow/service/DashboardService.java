package webtech.mid.fuel_flow.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import webtech.mid.fuel_flow.model.DeliveryStatus;
import webtech.mid.fuel_flow.repository.AttendantRepository;
import webtech.mid.fuel_flow.repository.DeliveryRepository;
import webtech.mid.fuel_flow.repository.PumpRepository;
import webtech.mid.fuel_flow.repository.SupplierRepository;
import webtech.mid.fuel_flow.repository.TankOffloadRepository;

@Service
public class DashboardService {

    private final SupplierRepository supplierRepository;
    private final AttendantRepository attendantRepository;
    private final PumpRepository pumpRepository;
    private final DeliveryRepository deliveryRepository;
    private final TankOffloadRepository tankOffloadRepository;

    public DashboardService(SupplierRepository supplierRepository, AttendantRepository attendantRepository,
            PumpRepository pumpRepository, DeliveryRepository deliveryRepository,
            TankOffloadRepository tankOffloadRepository) {
        this.supplierRepository = supplierRepository;
        this.attendantRepository = attendantRepository;
        this.pumpRepository = pumpRepository;
        this.deliveryRepository = deliveryRepository;
        this.tankOffloadRepository = tankOffloadRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("suppliers", supplierRepository.count());
        summary.put("attendants", attendantRepository.count());
        summary.put("freePumps", (long) pumpRepository.findFreePumps().size()); // reuses C2
        summary.put("deliveriesToday", deliveryRepository.countByDeliveryDate(LocalDate.now()));
        summary.put("ordered", deliveryRepository.countDeliveriesByStatus(DeliveryStatus.ORDERED));
        summary.put("tankOffloads", tankOffloadRepository.count());
        return summary;
    }
}

