/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.dao;

import com.epam.hotel.idao.IUserDao;
import com.epam.hotel.model.User;
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.util.List; 
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 * Dao for User entity
 * @author Alex
 */
public class MySqlUserDao extends AbstractDao<User> implements IUserDao{
    
    private static Logger log = Logger.getLogger(MySqlUserDao.class);
    
    @Override 
    protected String getSelectQuery() { 
        return "SELECT email, name, telephone FROM hotel.user"; 
    } 
    
    @Override 
    protected String getInsertQuery() { 
        return "INSERT INTO hotel.user (email, name, telephone) \n" + "VALUES (?, ?, ?);"; 
    } 
    
    @Override 
    protected String getUpdateQuery() { 
        return addPrimaryKeyQuery("UPDATE hotel.user SET email= ?, name = ?, telephone = ?"); 
    } 
    @Override 
    protected String getDeleteQuery() { 
        return addPrimaryKeyQuery("DELETE FROM hotel.user"); 
    }
    
    @Override
    protected String addPrimaryKeyQuery(String sql){
        return sql += " WHERE email= ?;"; 
    }
    
    @Override 
    public void add(User user){ 
        User existingUser = findByPK(user);
        if(existingUser == null){
            log.info("User is new");
            super.add(user);
        }else{
            log.info("User is old");
            update(user);
        }
    } 
    
    @Override
    protected List<User> parseResultSet(ResultSet rs) { 
        LinkedList<User> result = new LinkedList<User>(); 
        try { 
            while (rs.next()) { 
                User user = new User(); 
                user.setEmail(rs.getString("email"));  
                user.setName(rs.getString("name")); 
                user.setTelephone(rs.getString("telephone"));
                result.add(user); 
            } 
        } catch (Exception e) {
            log.error("ParseResultSet() error");
        } 
        return result; 
    } 
     
    @Override 
    protected void prepareStatementForPrimaryKey(PreparedStatement statement, User user){
        try { 
            statement.setString(1, user.getEmail());  
        } catch (Exception e) {
            log.error("Error in prepareStatementForPrimaryKey()");
        } 
    }
    
    @Override 
    protected void prepareStatementForInsert(PreparedStatement statement, User user) 
    { 
        try { 
            statement.setString(1, user.getEmail()); 
            statement.setString(2, user.getName());
            statement.setString(3, user.getTelephone());
        } catch (Exception e) { 
            log.error("Statement can't be set in prepareStatementForInsert()");
        } 
    } 
    
    @Override 
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) 
    { 
        try { 
            statement.setString(1, user.getEmail()); 
            statement.setString(2, user.getName()); 
            statement.setString(3, user.getTelephone());
            statement.setString(4, user.getEmail());
        } catch (Exception e) { 
            log.error("Statement can't be set in prepareStatementForUpdate()");
        } 
    } 
    
    /**
     * Non used and unchecked method. If it need - remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override 
    protected void prepareStatementForDelete(PreparedStatement statement, User user){
        try { 
            statement.setString(1, user.getEmail()); 
        } catch (Exception e) { 
            log.error("Statement can't be set in prepareStatementForDelete()");
            //exception handler; 
        } 
    }
    
    public MySqlUserDao(Connection connection) { 
        super(connection); 
    }
}
