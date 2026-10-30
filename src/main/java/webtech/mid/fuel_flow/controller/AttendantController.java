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

import webtech.mid.fuel_flow.model.Attendant;
import webtech.mid.fuel_flow.service.AttendantService;

@RestController
@RequestMapping("/api/attendants")
public class AttendantController {

    private AttendantService attendantService;

    public AttendantController(AttendantService attendantService){
        this.attendantService = attendantService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Attendant attendant) {
        return new ResponseEntity<>(attendantService.save(attendant), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attendant>> getAll() {
        return new ResponseEntity<>(attendantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Attendant> optional = attendantService.getById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The attendant with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Attendant attendant) {
        String message = attendantService.update(id, attendant);
        if (message.equals("Attendant updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = attendantService.delete(id);
        if (message.equals("Attendant deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
