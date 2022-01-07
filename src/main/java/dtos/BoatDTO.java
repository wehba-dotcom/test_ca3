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
public class BoatDTO {
    private long b_id;
    private String brand;
    private String make;
    private String name;
    List<Owner> owners;

    public BoatDTO(String brand, String make, String name,List<Owner> owners) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.owners= owners;

    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public static List<BoatDTO> getDtos(List<Boat> rms){
        List<BoatDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new BoatDTO(rm)));
        return rmdtos;
    }


    public BoatDTO(Boat rm) {
        if(rm.getB_id() != null)
            this.b_id = rm.getB_id();
        this.brand = rm.getBrand();
        this.make = rm.getMake();
        this.name=rm.getName();

        }

    public long getB_id() {
        return b_id;
    }

    public void setB_id(long b_id) {
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
