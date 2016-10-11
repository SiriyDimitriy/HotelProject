/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.model;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 * User entity from database
 * @author Alex
 */

public class User implements Serializable {

    private String email;
    private String name;
    private String telephone;
    private Collection<UsersOrder> usersOrderCollection;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String name, String telephone) {
        this.email = email;
        this.name = name;
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @XmlTransient
    public Collection<UsersOrder> getUsersOrderCollection() {
        return usersOrderCollection;
    }

    public void setUsersOrderCollection(Collection<UsersOrder> usersOrderCollection) {
        this.usersOrderCollection = usersOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        hash += (name != null ? name.hashCode() : 0);
        hash += (telephone != null ? telephone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.email == null && other.email != null) || 
                (this.email != null && !this.email.equals(other.email) &&
                !this.name.equals(other.name) && 
                !this.telephone.equals(other.telephone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.hotel.model.User[ email=" + email + " ]";
    }
    
}
