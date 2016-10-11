/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.model;

import java.io.Serializable;

/**
 * Admin primary key entity from database
 * @author Alex
 */

public class AdminPK implements Serializable {

    private String login;
    private String password;

    public AdminPK() {
    }

    public AdminPK(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        hash += (password != null ? password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminPK)) {
            return false;
        }
        AdminPK other = (AdminPK) object;
        if ((this.login == null && other.login != null) || 
                (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        if ((this.password == null && other.password != null) || 
                (this.password != null && !this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.hotel.model.AdminPK[ login=" + login + ", password=" + password + " ]";
    }
    
}
