/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.dao;

import com.epam.hotel.idao.IClassDao;
import com.epam.hotel.model.RoomsClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Dao for RoomsClass entity
 * @author Alex
 */
public class MySqlClassDao extends AbstractDao<RoomsClass> implements IClassDao{
    
    private static Logger log = Logger.getLogger(AbstractDao.class);
    
    @Override 
    protected String getSelectQuery() { 
        return "SELECT name, description, foto FROM hotel.class"; 
    } 
    
    @Override 
    protected String getInsertQuery() { 
        return "INSERT INTO hotel.class (name, description, foto) \n" + "VALUES (?, ?, ?);"; 
    } 
    
    @Override 
    protected String getUpdateQuery() { 
        return addPrimaryKeyQuery("UPDATE hotel.class SET name = ?, description = ?, foto = ?");
    } 
    @Override 
    protected String getDeleteQuery() { 
        return addPrimaryKeyQuery("DELETE FROM hotel.class"); 
    }
    
    @Override
    protected String addPrimaryKeyQuery(String sql){
        return sql += " WHERE name = ?;"; 
    }
    
    /**
     * Unrealized method. If it need - write it and remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override 
    public void add(RoomsClass roomClass) //throws PersistException
    { 
        super.add(roomClass); //may be finding by PK before super() or not
    } 
    
    @Override
    protected List<RoomsClass> parseResultSet(ResultSet rs){ 
        LinkedList<RoomsClass> result = new LinkedList<RoomsClass>(); 
        try { 
            while (rs.next()) { 
                RoomsClass roomClass = new RoomsClass(); 
                roomClass.setName(rs.getString("name"));  
                roomClass.setDescription(rs.getString("description"));
                roomClass.setFoto(rs.getString("foto"));
                result.add(roomClass); 
            } 
        } catch (Exception e) { 
            log.error("Error in parseResultSet()"); 
        } 
        return result; 
    } 
    
    @Override 
    protected void prepareStatementForPrimaryKey(PreparedStatement statement, RoomsClass roomClass){
        try { 
            statement.setString(1, roomClass.getName()); 
        } catch (Exception e) {
            log.error("Error in prepareStatementForPrimaryKey()");
        } 
    }
    
    /**
     * Non used and unchecked method. If it need - remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override  
    protected void prepareStatementForInsert(PreparedStatement statement, RoomsClass roomClass)
    { 
        try { 
            statement.setString(1, roomClass.getName()); 
            statement.setString(2, roomClass.getDescription()); 
            statement.setString(3, roomClass.getFoto());
        } catch (Exception e) { 
            //exception handler; 
        } 
    } 
    
    /**
     * Non used and unchecked method. If it need - remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override 
    protected void prepareStatementForUpdate(PreparedStatement statement, RoomsClass roomClass) //throws PersistException 
    { 
        try { 
            statement.setString(1, roomClass.getName()); 
            statement.setString(2, roomClass.getDescription()); 
            statement.setString(3, roomClass.getFoto());
        } catch (Exception e) { 
            //exception handler;
        } 
    } 
    
    /**
     * Non used and unchecked method. If it need - remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override 
    protected void prepareStatementForDelete(PreparedStatement statement, RoomsClass roomClass){
        try { 
            statement.setString(1, roomClass.getName()); 
        } catch (Exception e) { 
            //exception handler;
        } 
    }
    
    public MySqlClassDao(Connection connection) { 
        super(connection); 
    }
}
