package webtech.mid.fuel_flow.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webtech.mid.fuel_flow.model.Delivery;
import webtech.mid.fuel_flow.model.DeliveryStatus;
import webtech.mid.fuel_flow.service.DeliveryService;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Delivery delivery) {
        return new ResponseEntity<>(deliveryService.save(delivery), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Delivery>> getAll() {
        return new ResponseEntity<>(deliveryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Delivery> optional = deliveryService.getById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The delivery with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-status")
public ResponseEntity<List<Delivery>> getByStatus(@RequestParam DeliveryStatus status) {
    return new ResponseEntity<>(deliveryService.getByStatus(status), HttpStatus.OK);
}

@GetMapping("/between")
public ResponseEntity<List<Delivery>> getBetween(@RequestParam String start, @RequestParam String end) {
    return new ResponseEntity<>(deliveryService.getBetween(LocalDate.parse(start), LocalDate.parse(end)), HttpStatus.OK);
}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Delivery delivery) {
        String message = deliveryService.update(id, delivery);
        if (message.equals("Delivery updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = deliveryService.delete(id);
        if (message.equals("Delivery deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
