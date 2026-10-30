package webtech.mid.fuel_flow.controller;

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
import org.springframework.web.bind.annotation.RestController;

import webtech.mid.fuel_flow.model.FuelType;
import webtech.mid.fuel_flow.service.FuelTypeService;

@RestController
@RequestMapping("/api/fuel-types")
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    public FuelTypeController(FuelTypeService fuelTypeService){
        this.fuelTypeService = fuelTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FuelType fuelType) {
        return new ResponseEntity<>(fuelTypeService.save(fuelType), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FuelType>> getAll() {
        return new ResponseEntity<>(fuelTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<FuelType> optional = fuelTypeService.getById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The fuel type with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/unused")
public ResponseEntity<List<FuelType>> getUnused() {
    return new ResponseEntity<>(fuelTypeService.getUnused(), HttpStatus.OK);
}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody FuelType fuelType) {
        String message = fuelTypeService.update(id, fuelType);
        if (message.equals("Fuel type updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = fuelTypeService.delete(id);
        if (message.equals("Fuel type deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}