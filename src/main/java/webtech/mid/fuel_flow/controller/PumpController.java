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

import webtech.mid.fuel_flow.model.Pump;
import webtech.mid.fuel_flow.service.PumpService;

@RestController
@RequestMapping("/api/pumps")
public class PumpController {
    private PumpService pumpService;

    public PumpController(PumpService pumpService){
        this.pumpService = pumpService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Pump pump) {
        return new ResponseEntity<>(pumpService.save(pump), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pump>> getAll() {
        return new ResponseEntity<>(pumpService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Pump> optional = pumpService.getById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The pump with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Pump pump) {
        String message = pumpService.update(id, pump);
        if (message.equals("Pump updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = pumpService.delete(id);
        if (message.equals("Pump deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
