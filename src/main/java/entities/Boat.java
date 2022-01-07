package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Boat.deleteAllRows", query = "DELETE from Boat")
public class Boat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long b_id;
    private String brand;
    private String make;
    private String name;
@ManyToOne
    private Harbour harbour;
    @ManyToMany(mappedBy = "boates", cascade =CascadeType.PERSIST )
List<Owner> owners;

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
    }

    public Boat() {
    }

    public Boat(String brand, String make, String name) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.owners= new ArrayList<>();

    }

    public Long getB_id() {
        return b_id;
    }
    public void AddOwner(Owner owner){
        if(owner != null){
            this.owners.add(owner);
            owner.getBoates().add(this);
        }
    }

    public void setB_id(Long b_id) {
        this.b_id = b_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
