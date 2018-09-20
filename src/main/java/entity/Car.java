package entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findById", query = "select c from Car c where c.id=:id")
        /*@NamedQuery(name = "Car.changeMilage", query = "update Car c set c.km=:km") /* where c.id=:id*/
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Enumerated(EnumType.STRING)
    private CarBodyType carBodyType;

    @Column
    private String model;

    @Column
    private LocalDate productionDate;

    @Column
    private String color;

    @Column
    private Long km;

    @Transient
    public int getAge() {
        Period period = Period.between (getProductionDate (), LocalDate.now ());
        return period.getYears ();
    }

    @UpdateTimestamp
    private LocalDateTime modifiedDate/* = LocalDateTime.now ()*/;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CarBodyType getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(CarBodyType carBodyType) {
        this.carBodyType = carBodyType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getColor() {
        return color;
    }

    public void setColour(String color) {
        this.color = color;
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /*public int getAge() {
        Period period = Period.between (getProductionDate (), LocalDate.now ());
        return period.getYears ();
    }*/

    /*public void setAge(int age) {
        this.age = age;
    }*/

    public Car(String brand) {
        this.brand = brand;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car " +
                "id: " + id +
                ", " + brand +
                ", " + model +
                ", " + carBodyType +
                ", Production Date: " + productionDate +
                ", color: " + color +
                ", mileage: " + km + " km"
                ;
    }

    public Car(Long id, String brand, CarBodyType carBodyType, String model, LocalDate productionDate, String color, Long km, LocalDateTime modifiedDate) {
        this.id = id;
        this.brand = brand;
        this.carBodyType = carBodyType;
        this.model = model;
        this.productionDate = productionDate;
        this.color = color;
        this.km = km;
        this.modifiedDate = modifiedDate;
    }
}
