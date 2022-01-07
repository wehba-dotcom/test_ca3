package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Harbour.deleteAllRows", query = "DELETE from Harbour")
public class Harbour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long h_id;
    private String name;
    private String address;
    private int capacity;

    @OneToMany(mappedBy = "harbour", cascade =CascadeType.PERSIST )
    List<Boat>  boates;


    public Harbour(String name, String address, int capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.boates= new ArrayList<>();
    }

    public List<Boat> getBoates() {
        return boates;
    }

    public void addBoat(Boat boat) {
        this.boates.add(boat);
        if(boat != null){
            boat.setHarbour(this);
        }
    }

    public Harbour() {
    }

    public Long getH_id() {
        return h_id;
    }

    public void setH_id(Long h_id) {
        this.h_id = h_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setBoates(List<Boat> boates) {
        this.boates = boates;
    }
}
