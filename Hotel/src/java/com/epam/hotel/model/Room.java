/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.model;

import java.io.Serializable;

/**
 * Room entity from database
 * @author Alex
 */

public class Room implements Serializable {

    private Short number;
    private int places;
    private int price;
    private String comment;
    private RoomsClass class1;

    public Room() {
    }

    public Room(Short number) {
        this.number = number;
    }

    public Room(Short number, int places, int price) {
        this.number = number;
        this.places = places;
        this.price = price;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RoomsClass getClass1() {
        return class1;
    }

    public void setClass1(RoomsClass class1) {
        this.class1 = class1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        hash += places + price;
        hash += (comment != null ? comment.hashCode() : 0);
        hash += (class1 != null ? class1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.number == null && other.number != null) || 
                (this.number != null && !this.number.equals(other.number) && 
                !this.comment.equals(other.comment) && !this.class1.equals(other.class1) 
                && this.places != other.places && this.price != other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.hotel.model.Room[ number=" + number + " ]";
    }
    
}
