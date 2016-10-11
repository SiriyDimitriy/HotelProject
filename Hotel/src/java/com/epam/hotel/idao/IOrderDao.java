/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.idao;

import com.epam.hotel.model.User;
import com.epam.hotel.model.UsersOrder;
import java.util.List;

/**
 * Iterface for order DAO, has next specific methods:
 * @author Alex
 */
public interface IOrderDao extends IGenericDao<UsersOrder>{
    
    /**
     * Select last order with maximum value of 'id'
     * @return found order
     */
    public UsersOrder findLastOrder();
    
    /**
     * Select records with 'enddate' later now date
     * @return collection of such orders
     */
    public List<UsersOrder> findActualOrders();
    
    /**
     * Select all records for current User
     * @param user
     * @return collection of order for given user
     */
    public List<UsersOrder> findUsersOrders(User user);
    
    /**
     * Must update and return updated order
     * Remake it !!!!!!!
     * @param order
     * @param s
     * @return 
     */
    public UsersOrder add(UsersOrder order, boolean s);
    
}
