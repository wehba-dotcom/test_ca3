package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Owner.deleteAllRows", query = "DELETE from Owner")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private int phone;
@ManyToMany
    List<Boat> boates;

    public List<Boat> getBoates() {
        return boates;
    }

    public void setBoates(List<Boat> boates) {
        this.boates = boates;
    }

    public Owner() {
    }

    public Owner(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.boates= new ArrayList<>();
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner(List<Boat> boates) {
        this.boates = boates;
    }
}
