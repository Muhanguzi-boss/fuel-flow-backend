package webtech.mid.fuel_flow.model;


import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "tank_offload")

public class TankOffload {
@Id 
@GeneratedValue(strategy = GenerationType.UUID)

private UUID id;
private String tankName;
private String remarks;
private int litres;
private LocalDate offloadDate;

@ManyToOne 
@JoinColumn (name = "delivery_id")
private Delivery delivery;

public TankOffload() {
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public String getTankName() {
    return tankName;
}

public void setTankName(String tankName) {
    this.tankName = tankName;
}

public String getRemarks() {
    return remarks;
}

public void setRemarks(String remarks) {
    this.remarks = remarks;
}

public int getLitres() {
    return litres;
}

public void setLitres(int litres) {
    this.litres = litres;
}

public LocalDate getOffloadDate() {
    return offloadDate;
}

public void setOffloadDate(LocalDate offloadDate) {
    this.offloadDate = offloadDate;
}

public Delivery getDelivery() {
    return delivery;
}

public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
}




}
