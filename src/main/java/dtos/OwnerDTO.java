/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class OwnerDTO {
    private long id;
    private String name;
    private String address;
    private int phone;
    List<Boat> boats;

    public OwnerDTO(String name, String address, int phone,List<Boat> boats) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.boats= boats;
    }

    public static List<OwnerDTO> getDtos(List<Owner> rms){
        List<OwnerDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm->rmdtos.add(new OwnerDTO(rm)));
        return rmdtos;
    }


    public OwnerDTO(Owner rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.name = rm.getName();
        this.address = rm.getAddress();
        this.phone=rm.getPhone();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
