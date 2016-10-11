/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.idao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Persistant interface
 *
 * @param <T> type of persistant object
 * @author Alex
 */
public interface IGenericDao<T extends Serializable> {

    /**
     * Create new record corresponding to object
     */
    public void add(T object) throws SQLException;

    /**
     * Save new object State
     */
    public void update(T object) throws SQLException;

    /**
     * Delete record about object
     */
    public void delete(T object) throws SQLException;

    /**
     * Return list of objects corresponding to all records
     */
    public List<T> findAll() throws SQLException;

    /**
     * Return new objects from record corresponding to another with same primary key
     */
    public T findByPK(T object) throws SQLException;
}  

