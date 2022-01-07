/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Harbour;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class HarbourDTO {
    private Long h_id;
    private String name;
    private String address;
    private int capacity;
    List<BoatDTO> botes;

    public List<BoatDTO> getBotes() {
        return botes;
    }

    public void setBotes(List<BoatDTO> botes) {
        this.botes = botes;
    }

    public static List<HarbourDTO> getDtos(List<Harbour> rms){
        List<HarbourDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new HarbourDTO(rm)));
        return rmdtos;
    }


    public HarbourDTO(Harbour rm) {
        if(rm.getH_id() != null)
            this.h_id = rm.getH_id();
        this.name = rm.getName();
        this.address = rm.getAddress();
        this.capacity=rm.getCapacity();
        this.botes= BoatDTO.getDtos(rm.getBoates());
    }

    public HarbourDTO(String name, String address, int capacity, List<BoatDTO> boates) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.botes= boates;
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
}
