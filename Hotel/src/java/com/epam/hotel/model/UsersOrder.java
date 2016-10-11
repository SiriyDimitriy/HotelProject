/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Orders entity from database 
 * @author Alex
 */

public class UsersOrder implements Serializable {

    private Integer id;
    private Date orderdate;
    private String class1;
    private int places;
    private Date startdate;
    private Date enddate;
    private String usercomment;
    private Short roomnumber;
    private Integer cost;
    private User useremail;
    private List<Room> roomsList;

    public UsersOrder() {
    }

    public UsersOrder(Integer id) {
        this.id = id;
    }

    public UsersOrder(Integer id, Date orderdate, String class1, int places, Date startdate, Date enddate) {
        this.id = id;
        this.orderdate = orderdate;
        this.class1 = class1;
        this.places = places;
        this.startdate = startdate;
        this.enddate = enddate;
    }
    
    public List<Room> getRoomslist(){
        return roomsList;
    }
    
    public void setRoomslist(List<Room> roomsList){
        this.roomsList = roomsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    public Short getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(Short roomnumber) {
        this.roomnumber = roomnumber;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public User getUseremail() {
        return useremail;
    }

    public void setUseremail(User useremail) {
        this.useremail = useremail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (orderdate != null ? orderdate.hashCode() : 0);
        hash += (useremail != null ? useremail.hashCode() : 0);
        hash += (class1 != null ? class1.hashCode() : 0);
        hash += places;
        hash += (startdate != null ? startdate.hashCode() : 0);
        hash += (roomnumber != null ? roomnumber.hashCode() : 0);
        hash += (cost != null ? cost.hashCode() : 0);
        hash += (enddate != null ? enddate.hashCode() : 0);
        hash += (usercomment != null ? usercomment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersOrder)) {
            return false;
        }
        UsersOrder other = (UsersOrder) object;
        if ((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id) &&
                !this.orderdate.equals(other.orderdate) && 
                !this.useremail.equals(other.useremail) &&
                !this.class1.equals(other.class1) &&
                !this.startdate.equals(other.startdate) &&
                !this.enddate.equals(other.enddate) &&
                !this.usercomment.equals(other.usercomment) &&
                !this.roomnumber.equals(other.roomnumber) &&
                !this.cost.equals(other.cost) &&
                this.places != other.places)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.hotel.model.UsersOrder[ id=" + id + " ]";
    }
    
    public String toJson(){
        return "{\"id\":" + id +",\"orderdate\":\"" + orderdate.toString() + 
                "\",\"useremail\":\"" + useremail.getEmail() + "\",\"class\":\"" +
                class1 +"\",\"places\":" + places + ",\"startdate\":\"" + startdate + 
                "\",\"enddate\":\"" + enddate + "\",\"usercomment\":\"" + usercomment +
                "\",\"roomnumber\":" + roomnumber + ",\"cost\":" + cost +"}";
    }
}
