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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webtech.mid.fuel_flow.model.Supplier;
import webtech.mid.fuel_flow.service.AttendantService;
import webtech.mid.fuel_flow.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final AttendantService attendantService;
    public SupplierController(SupplierService supplierService, AttendantService attendantService){
        this.supplierService = supplierService;
        this.attendantService = attendantService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.save(supplier), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Supplier>> getAll() {
        return new ResponseEntity<>(supplierService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Supplier> optional = supplierService.getById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The supplier with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-company-name")
public ResponseEntity<List<Supplier>> getByCompanyName(@RequestParam String companyName) {
    return new ResponseEntity<>(supplierService.getByCompanyName(companyName), HttpStatus.OK);
}

@GetMapping("/of-attendant/{attendantId}")
public ResponseEntity<?> getByAttendant(@PathVariable UUID attendantId) {
    if (!attendantService.existsById(attendantId)) {
        return new ResponseEntity<>("The attendant with that id does not exist", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(supplierService.getByAttendant(attendantId), HttpStatus.OK);
}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Supplier supplier) {
        String message = supplierService.update(id, supplier);
        if (message.equals("Supplier updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = supplierService.delete(id);
        if (message.equals("Supplier deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}