package webtech.mid.fuel_flow.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webtech.mid.fuel_flow.model.TankOffload;
import webtech.mid.fuel_flow.service.TankOffloadService;

@RestController
@RequestMapping("/api/tank-offloads")
public class TankOffloadController {

    private final TankOffloadService tankOffloadService;

    public TankOffloadController(TankOffloadService tankOffloadService) {
        this.tankOffloadService = tankOffloadService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID deliveryId, @RequestBody TankOffload tankOffload) {
        String message = tankOffloadService.save(deliveryId, tankOffload);
        if (message.endsWith("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("Tank offloads are only for RECEIVED deliveries")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TankOffload>> getAll() {
        return new ResponseEntity<>(tankOffloadService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/supplier/{supplierCode}")
    public ResponseEntity<List<TankOffload>> getBySupplierCode(@PathVariable String supplierCode) {
        return new ResponseEntity<>(tankOffloadService.getBySupplierCode(supplierCode), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = tankOffloadService.delete(id);
        if (message.endsWith("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
