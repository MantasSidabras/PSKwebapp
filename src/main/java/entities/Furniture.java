package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.io.Serializable;

@Entity
@NamedQueries({
    @NamedQuery(name = "Furniture.findAll", query = "select a from Furniture as a")
})
@Table(name = "Furniture")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String type;

    @Size(max = 50)
    @Column(name = "MATERIAL")
    private String material;

    @Column(name = "PRICE")
    private double price;

    public Furniture(){
        this.type = "no type";
        this.material = "no material";
        this.price = 0;
    }

    public Furniture(String type, String material, float price){
        this.type = type;
        this.material = material;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return Objects.equals(type, furniture.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}