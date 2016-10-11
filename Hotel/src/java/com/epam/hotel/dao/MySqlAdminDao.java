/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.dao;

import com.epam.hotel.idao.IAdminDao;
import com.epam.hotel.model.Admin;
import com.epam.hotel.model.AdminPK;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Dao for Admin entity
 * @author Alex
 */
public class MySqlAdminDao extends AbstractDao<Admin> implements IAdminDao{
    
    private static Logger log = Logger.getLogger(MySqlAdminDao.class);
    
    @Override 
    protected String getSelectQuery() { 
        return "SELECT login, password FROM hotel.admin"; 
    } 
    
    @Override 
    protected String getInsertQuery() { 
        return "INSERT INTO hotel.admin (login, password) VALUES (?, ?);"; 
    } 
    
    @Override 
    protected String getUpdateQuery() { 
        return addPrimaryKeyQuery("UPDATE hotel.admin SET login = ?, password = ?");
    } 
    @Override 
    protected String getDeleteQuery() { 
        return addPrimaryKeyQuery("DELETE FROM hotel.admin"); 
    }
    
    @Override
    protected String addPrimaryKeyQuery(String sql){
        return sql += " WHERE login = ? AND password = ?;"; 
    }
    
    /**
     * Unrealized method. If it need - write it and remove the anotation
     * @deprecated
     */
    @Deprecated
    @Override
    public void add(Admin admin) //throws PersistException
    { 
        super.add(admin); //may be finding by PK before super() or not
    } 
    
    @Override
    protected List<Admin> parseResultSet(ResultSet rs){ 
        LinkedList<Admin> result = new LinkedList<Admin>(); 
        try { 
            while (rs.next()) { 
                Admin admin = new Admin(); 
                AdminPK adminPK = new AdminPK();
                adminPK.setLogin(rs.getString("login"));
                adminPK.setPassword("password");
                admin.setAdminPK(adminPK);
                result.add(admin); 
            } 
        } catch (Exception e) { 
            log.error("Error in parseResultSet()");
        } 
        return result; 
    } 
    
    @Override 
    protected void prepareStatementForPrimaryKey(PreparedStatement statement, Admin admin){
        try { 
            statement.setString(1, admin.getAdminPK().getLogin()); 
            statement.setString(2, admin.getAdminPK().getPassword()); 
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
    protected void prepareStatementForInsert(PreparedStatement statement, Admin admin) //throws PersistException 
    { 
        try { 
            statement.setString(1, admin.getAdminPK().getLogin()); 
            statement.setString(2, admin.getAdminPK().getPassword()); 
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
    protected void prepareStatementForUpdate(PreparedStatement statement, Admin admin) //throws PersistException 
    { 
        try { 
            statement.setString(1, admin.getAdminPK().getLogin()); 
            statement.setString(2, admin.getAdminPK().getPassword()); 
            statement.setString(3, admin.getAdminPK().getLogin()); 
            statement.setString(4, admin.getAdminPK().getPassword());
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
    protected void prepareStatementForDelete(PreparedStatement statement, Admin admin){
        try { 
            statement.setString(1, admin.getAdminPK().getLogin()); 
            statement.setString(2, admin.getAdminPK().getPassword()); 
        } catch (Exception e) { 
            //exception handler; 
        } 
    }
    
    public MySqlAdminDao(Connection connection) { 
        super(connection); 
    }
}
