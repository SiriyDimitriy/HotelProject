/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.dao;

import com.epam.hotel.idao.IRoomDao;
import com.epam.hotel.model.RoomsClass;
import com.epam.hotel.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
/**
 *
 * @author Alex
 */
public class MySqlRoomDao extends AbstractDao<Room> implements IRoomDao{
    
    private static Logger log = Logger.getLogger(MySqlRoomDao.class);
    
    @Override 
    protected String getSelectQuery() { 
        return "SELECT number, class, places, price, comment FROM hotel.room"; 
    } 
    
    @Override 
    protected String getInsertQuery() { 
        return "INSERT INTO hotel.room (number, class, places, price, comment) "
                + "VALUES (?, ?, ?, ?, ?);"; 
    } 
    
    @Override 
    protected String getUpdateQuery() { 
        return addPrimaryKeyQuery("UPDATE hotel.room SET number = ?, class = ?, "
                + "places = ?, price = ?, comment = ?"); 
    } 
    @Override 
    protected String getDeleteQuery() { 
        return addPrimaryKeyQuery("DELETE FROM hotel.room"); 
    }
    
    @Override
    protected String addPrimaryKeyQuery(String sql){
        return sql += " WHERE number = ?;"; 
    }
    
    /**
     * Make SQL query for select unique 'places'
     * @return 
     */
    protected String selectPlacesQuery(){
        return "SELECT places FROM hotel.room GROUP BY places;";
    }
    
    /**
     * Continiue SQL query by setting unique combination of 'class' and 'places' column
     * @return 
     */
    protected String selectPricesQuery(){
        return "SELECT DISTINCT class, places, price FROM room ORDER BY class";
    }
     
    /**
     * Unrealized method. If it need - write it and remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override
    public void add(Room room) { 
        super.add(room); //may be finding by PK before super() or not
    }  
    
    @Override
    public List<Integer> findAvailablePlaces (){
        ArrayList<Integer> array = new ArrayList<Integer>();
        String sql = selectPlacesQuery(); 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                array.add(rs.getInt("places"));
            } 
            rs.close();
        } catch (Exception e) { 
            log.error("Invalid statement for availablePlaces");
        }
        if(array.isEmpty()){
            log.info("Array of available places is empty");
        }
        return array;
    }
    
    @Override
    public List<Room> findPrices(){
        LinkedList<Room> result = new LinkedList<Room>();
        MySqlClassDao classDao = new MySqlClassDao(connection);
        String sql = selectPricesQuery(); 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Room room = new Room(); 
                RoomsClass temp = new RoomsClass();
                temp.setName(rs.getString("class"));
                RoomsClass roomCl = classDao.findByPK(temp);
                room.setClass1(roomCl);
                room.setPlaces(rs.getInt("places"));
                room.setPrice(rs.getInt("price"));
                result.add(room); 
            } 
            rs.close();
        } catch (Exception e) { 
            log.error("Invalid statement for prices");
        }
        return result;
    }
    
    @Override
    protected List<Room> parseResultSet(ResultSet rs){ 
        LinkedList<Room> result = new LinkedList<Room>(); 
        MySqlClassDao classDao = new MySqlClassDao(connection);
        try {
            while (rs.next()) { 
                Room room = new Room(); 
                RoomsClass temp = new RoomsClass();
                temp.setName(rs.getString("class"));
                RoomsClass roomCl = classDao.findByPK(temp);
                
                room.setNumber(rs.getShort("number"));  
                room.setClass1(roomCl);
                room.setPlaces(rs.getInt("places"));
                room.setPrice(rs.getInt("price"));
                room.setComment(rs.getString("comment"));
                result.add(room); 
            } 
        } catch (Exception e) { 
            log.error("parseResultSet() error");
        } 
        return result; 
    } 
    
    @Override 
    protected void prepareStatementForPrimaryKey(PreparedStatement statement, Room room){
        try { 
            statement.setShort(1, room.getNumber());
        } catch (Exception e) {
            log.error("Error in prepareStatementForPrimaryKey()"); 
        } 
    }
    
   /**
     * Non used and unchecked method. If it need - remove the anotation
     * @param room
     * @deprecated
     */
    @Deprecated
    @Override  
    protected void prepareStatementForInsert(PreparedStatement statement, Room room) //throws PersistException 
    { 
        try { 
            statement.setShort(1, room.getNumber()); 
            statement.setString(2, room.getClass1().getName()); 
            statement.setInt(3, room.getPlaces());
            statement.setInt(4, room.getPrice());
            statement.setString(5, room.getComment());
        } catch (Exception e) { 
            //exception handler; 
        } 
    } 
    
    @Override  
    protected void prepareStatementForUpdate(PreparedStatement statement, Room room) 
    { 
        try { 
            statement.setShort(1, room.getNumber()); 
            statement.setString(2, room.getClass1().getName()); 
            statement.setInt(3, room.getPlaces());
            statement.setInt(4, room.getPrice());
            statement.setString(5, room.getComment());
            statement.setShort(6, room.getNumber());
        } catch (Exception e) { 
            log.error("Error in prepareStatementForUpdate()");
        } 
    } 
    
    /**
     * Non used and unchecked method. If it need - remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override 
    protected void prepareStatementForDelete(PreparedStatement statement, Room room){
        try { 
            statement.setInt(1, room.getNumber()); 
        } catch (Exception e) { 
            //exception handler; 
        } 
    }
    
    public MySqlRoomDao(Connection connection) { 
        super(connection); 
    }
}
