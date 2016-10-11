/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.idao;

import com.epam.hotel.model.Room;
import java.util.List;

/**
 * Iterface for room DAO, has next specific methods:
 * @author Alex
 */
public interface IRoomDao extends IGenericDao<Room>{
    
    /**
     * Select unique values of column 'places'
     * @return list of these values
     */
    public List<Integer> findAvailablePlaces ();
    
    /**
     * Select all unique combination of columns 'places' and 'classes' 
     * @return corresponding list of Rooms with filds 'places' and 'classes'
     */
    public List<Room> findPrices();
    
}
