/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.dao;

import com.epam.hotel.model.UsersOrder;
import com.epam.hotel.model.User;
import com.epam.hotel.idao.IOrderDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Dao for UsersOrder entity 
 * @author Alex
 */
public class MySqlOrderDao extends AbstractDao<UsersOrder> implements IOrderDao{

    private static Logger log = Logger.getLogger(MySqlOrderDao.class);

    @Override
    protected String getSelectQuery() {
        return "SELECT id, orderdate, useremail, class, places, startdate, enddate, "
                + "usercomment, roomnumber, cost FROM hotel.order";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO hotel.order (orderdate, useremail, class, places, startdate,"
                + " enddate, usercomment) VALUES (NOW(), ?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return addPrimaryKeyQuery("UPDATE hotel.order SET orderdate= ?, "
                + "useremail = ?, class = ?, places =?, startdate = ?, "
                + "enddate = ?, usercomment = ?, roomnumber = ?, cost = ?");
    }

    @Override
    protected String getDeleteQuery() {
        return addPrimaryKeyQuery("DELETE FROM hotel.order");
    }

    @Override
    protected String addPrimaryKeyQuery(String sql) {
        return sql += " WHERE id = ?;";
    }

    /**
     * Continiue SQL query by setting 'enddate' column parameter
     * @param sql
     * @return 
     */
    protected String addActualOrderQuery(String sql) {
        return sql += " WHERE enddate > NOW();";
    }

    /**
     * Continiue SQL query by setting current User
     * @param sql
     * @return 
     */
    protected String addUsersOrderQuery(String sql) {
        return sql += " WHERE useremail = ?;";
    }

    /**
     * Continiue SQL query by setting last record
     * @param sql
     * @return 
     */
    protected String addMaxIdQuery(String sql) {
        return sql += " WHERE id=(select MAX(id) from hotel.order);";
    }

    @Override
    public UsersOrder findLastOrder() {
        List<UsersOrder> list = null;
        String sql = addMaxIdQuery(getSelectQuery());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            rs.close();
            if (list == null || list.size() == 0) {
                return null;
            }
        } catch (Exception e) {
            log.error("Error in readActualOrders()");
        }
        return list.iterator().next();
    }

    @Override
    public List<UsersOrder> findActualOrders() {
        List<UsersOrder> list = null;
        log.info("We are in readActualOrders()");
        String sql = addActualOrderQuery(getSelectQuery());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            rs.close();
        } catch (Exception e) {
            log.error("Error in readActualOrders()");
        }
        return list;
    }

    @Override
    public List<UsersOrder> findUsersOrders(User user) {
        List<UsersOrder> list = null;
        log.info("We are in readUsersOrders()");
        String sql = addUsersOrderQuery(getSelectQuery());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            rs.close();
        } catch (Exception e) {
            log.error("Error in readUsersOrders()"); 
        }
        return list;
    }

    /**
     * This method doesn't work.
     * The reason is impossibility to create right SQL query without prepare statemrnt,
     * becouse of using getGeneratedKeys()fnction
     * @param order order for update
     * @param s make this method overloaded
     * @return updated order
     * @deprecated
     */
    @Deprecated
    public UsersOrder add(UsersOrder order, boolean s){  
        String sql = "INSERT INTO hotel.order (orderdate, useremail, class, places, startdate,"
                + " enddate, usercomment) VALUES (NOW(), \"" + order.getUseremail().getEmail() + 
                "\", \"" + order.getClass1() + "\", " + order.getPlaces() + 
                ", " + order.getStartdate() + ", " + order.getEnddate() + 
                ", \"" + order.getUsercomment() + "\");";
        UsersOrder insertedOrder = null;
        log.info("sql custom insert");
        
        log.info(sql);
        try (Statement statement = connection.createStatement()) { 
            //prepareStatementForInsert(statement, order);
            int count = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                insertedOrder = findByPK(new UsersOrder(rs.getInt(1)));
            }catch(Exception e){
                log.error("custom insert order resultset error");
            }
            if (count != 1) { 
                log.error("insert" + order + "failed");
            } 
        } catch (Exception e) { 
            log.error("DB error in custom insert");
        }
        return insertedOrder;
    }
    
    @Override
    protected List<UsersOrder> parseResultSet(ResultSet rs){
        LinkedList<UsersOrder> result = new LinkedList<UsersOrder>();
        try {
            MySqlUserDao userDao = new MySqlUserDao(connection);
            while (rs.next()) {
                UsersOrder order = new UsersOrder();
                User temp = new User();
                temp.setEmail(rs.getString("useremail"));
                User user = userDao.findByPK(temp);
                order.setId(rs.getInt("id"));
                order.setOrderdate(rs.getDate("orderdate"));
                order.setUseremail(user);
                order.setClass1(rs.getString("class"));
                order.setPlaces(rs.getInt("places"));
                order.setStartdate(rs.getDate("startdate"));
                order.setEnddate(rs.getDate("enddate"));
                order.setUsercomment(rs.getString("usercomment"));
                order.setRoomnumber(rs.getShort("roomnumber"));
                order.setCost(rs.getInt("cost"));
                result.add(order);
            }
        } catch (Exception e) {
            log.error("parseResultSet() error"); 
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UsersOrder order){
        try {
            statement.setString(1, order.getUseremail().getEmail());
            statement.setString(2, order.getClass1());
            statement.setInt(3, order.getPlaces());
            statement.setDate(4, new java.sql.Date(order.getStartdate().getTime()));
            statement.setDate(5, new java.sql.Date(order.getEnddate().getTime()));
            statement.setString(6, order.getUsercomment());
        } catch (Exception e) {
            log.error("prepareStatementForInsert() error");
        }
    }

    @Override
    protected void prepareStatementForPrimaryKey(PreparedStatement statement, UsersOrder order) {
        try {
            statement.setInt(1, order.getId());
        } catch (Exception e) {
            log.error("Error in prepareStatementForPrimaryKey()");
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UsersOrder order){
        try{
            statement.setDate(1, new java.sql.Date(order.getOrderdate().getTime())); 
            statement.setString(2, order.getUseremail().getEmail()); 
            statement.setString(3, order.getClass1()); 
            statement.setInt(4, order.getPlaces()); 
            statement.setDate(5, new java.sql.Date(order.getStartdate().getTime()));
            statement.setDate(6, new java.sql.Date(order.getEnddate().getTime()));
            statement.setString(7, order.getUsercomment());
            statement.setShort(8, order.getRoomnumber());
            statement.setInt(9, order.getCost());
            statement.setInt(10, order.getId()); 
        } catch (Exception e) { 
            log.error("Error in prepareStatementForUpdate()"); 
        } 
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, UsersOrder order) {
        try {
            statement.setInt(1, order.getId());
        } catch (Exception e) {
            log.error("Error in prepareStatementForDelete()"); 
        }
    }

    public MySqlOrderDao(Connection connection) {
        super(connection);
    }
}
