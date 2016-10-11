/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Room's class entity from database
 * @author Alex
 */

public class RoomsClass implements Serializable {

    private String name;
    private String description;
    private String foto;
    private Collection<Room> roomCollection;

    public RoomsClass() {
    }

    public RoomsClass(String name) {
        this.name = name;
    }

    public RoomsClass(String name, String description, String foto) {
        this.name = name;
        this.description = description;
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Collection<Room> getRoomCollection() {
        return roomCollection;
    }

    public void setRoomCollection(Collection<Room> roomCollection) {
        this.roomCollection = roomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (foto != null ? foto.hashCode() : 0);
        hash += (description != null ? description.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomsClass)) {
            return false;
        }
        RoomsClass other = (RoomsClass) object;
        if ((this.name == null && other.name != null) || 
                (this.name != null && !this.name.equals(other.name) &&
                !this.description.equals(other.description) &&
                !this.foto.equals(other.foto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.hotel.model.RoomsClass[ name=" + name + " ]";
    }
    
}
