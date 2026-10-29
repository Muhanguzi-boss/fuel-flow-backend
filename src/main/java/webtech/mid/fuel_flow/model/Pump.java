package webtech.mid.fuel_flow.model;

import java.util.UUID;
import jakarta.persistence.*;

@Entity 
@Table(name = "pump")
public class Pump {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
private int pumpNumber;
private String name;
public Pump() {
}
public UUID getId() {
    return id;
}
public void setId(UUID id) {
    this.id = id;
}
public int getPumpNumber() {
    return pumpNumber;
}
public void setPumpNumber(int pumpNumber) {
    this.pumpNumber = pumpNumber;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}



}
