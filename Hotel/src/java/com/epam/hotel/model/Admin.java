/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.model;

import java.io.Serializable;

/**
 * Admin entity from database
 * @author Alex
 */
public class Admin implements Serializable {

    protected AdminPK adminPK;

    public Admin() {
    }

    public Admin(AdminPK adminPK) {
        this.adminPK = adminPK;
    }

    public Admin(String login, String password) {
        this.adminPK = new AdminPK(login, password);
    }

    public AdminPK getAdminPK() {
        return adminPK;
    }

    public void setAdminPK(AdminPK adminPK) {
        this.adminPK = adminPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminPK != null ? adminPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.adminPK == null && other.adminPK != null) || 
                (this.adminPK != null && !this.adminPK.equals(other.adminPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.hotel.model.Admin[ adminPK=" + adminPK + " ]";
    }
    
}
