/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.logic;

import com.epam.hotel.daofactory.MySqlDaoFactory;
import com.epam.hotel.dao.*;
import com.epam.hotel.model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Working with dao factory and entity's daos as Reciever from Command pattern
 *
 * @author Alex
 */
public class DaoHandler {
    
    private static Logger log = Logger.getLogger(DaoHandler.class);
    private static MySqlDaoFactory daoFactory = MySqlDaoFactory.instance();

    /**
     * Select list of all available rooms classes
     *
     * @return
     */
    public List<RoomsClass> getRoomsClassesAll() {
        MySqlClassDao classDao = daoFactory.getDao(MySqlClassDao.class);
        List<RoomsClass> classList = classDao.findAll();
        classDao.closeConnection();
        return classList;
    }

    /**
     * Select list of unique combination of room's classes, places and prices in
     * object room
     *
     * @return
     */
    public List<Room> getRoomsPriceList() {
        MySqlRoomDao roomDao = daoFactory.getDao(MySqlRoomDao.class);
        List<Room> rooms = roomDao.findPrices();
        roomDao.closeConnection();
        return rooms;
    }

    /**
     * Insert user or Update existing
     *
     * @param data Map of new data about user
     * @return new or updated user
     */
    public User createUser(Map<String, String> data) {
        User user = new User();
        user.setName(data.get("name"));
        user.setEmail(data.get("email"));
        user.setTelephone(data.get("telephone"));
        MySqlUserDao userDao = daoFactory.getDao(MySqlUserDao.class);
        userDao.add(user);
        User newUser = userDao.findByPK(user);
        userDao.closeConnection();
        return newUser;
    }

    /**
     * Insert new order
     *
     * @param data Map of data about new order
     * @param user User that made an order
     * @return new order
     */
    public UsersOrder createOrder(Map<String, String> data, User user) {
        UsersOrder order = new UsersOrder();
        order.setId(null);
        Date d = new Date();
        order.setOrderdate(d);
        log.info("Current date is");
        log.info(d);
        order.setUseremail(user);
        order.setClass1(data.get("class"));
        order.setPlaces(new Integer(data.get("places")));

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date docDate = format.parse(data.get("dateFrom"));
            log.info(format.format(docDate));
            order.setStartdate(docDate);
            docDate = format.parse(data.get("dateTo"));
            order.setEnddate(docDate);
        } catch (ParseException e) {
            log.info("This is parse exception");
        }
        order.setUsercomment(data.get("comment"));
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        orderDao.add(order);
        UsersOrder lastOrder = orderDao.findLastOrder();
        orderDao.closeConnection();
        return lastOrder;
    }

    /**
     * Delete users order
     *
     * @param id PK of order
     * @return deleted order
     */
    public UsersOrder deleteOrder(int id) {
        UsersOrder order = new UsersOrder();
        order.setId(id);
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        UsersOrder deletedOrder = orderDao.findByPK(order);
        orderDao.delete(order);
        orderDao.closeConnection();
        log.info("order may be deleted");
        return deletedOrder;
    }

    /**
     * check login and password if contained in database
     *
     * @param login
     * @param password
     * @return true if contained
     */
    public boolean isAdmin(String login, String password) {
        Admin possibleAdmin = new Admin();
        AdminPK adminPrimaryKey = new AdminPK();
        adminPrimaryKey.setLogin(login);
        adminPrimaryKey.setPassword(password);
        possibleAdmin.setAdminPK(adminPrimaryKey);
        MySqlAdminDao adminDao = daoFactory.getDao(MySqlAdminDao.class);
        Admin admin = adminDao.findByPK(possibleAdmin);
        adminDao.closeConnection();
        return (admin != null);
    }

    /**
     * Select all actual orders for current date with the list of available
     * rooms per every order as part of order object
     *
     * @return
     */
    public List<UsersOrder> getActualOrdersWithAvailableRoomsList() {
        log.info("We are in containAdminOrders()");
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        List<UsersOrder> orderList = orderDao.findActualOrders();
        orderDao.closeConnection();
        MySqlRoomDao roomDao = daoFactory.getDao(MySqlRoomDao.class);
        for (UsersOrder order : orderList) {

            List<Room> rooms = roomDao.findAll();

            for (Room room : rooms) {
                if (!room.getClass1().getName().equals(order.getClass1())) {
                    room.setNumber(Short.parseShort("0"));
                 }
                if (room.getPlaces() != order.getPlaces()) {
                    room.setNumber(Short.parseShort("0"));
                }
                for (UsersOrder anotherOrder : orderList) {
                    if (room.getNumber() == anotherOrder.getRoomnumber()) {
                        if (order.getStartdate().compareTo(anotherOrder.getEnddate()) <= 0 && order.getStartdate().compareTo(anotherOrder.getStartdate()) >= 0) {
                            room.setNumber(Short.parseShort("0"));
                        }
                        if (order.getEnddate().compareTo(anotherOrder.getStartdate()) >= 0 && order.getEnddate().compareTo(anotherOrder.getEnddate()) <= 0) {
                            room.setNumber(Short.parseShort("0"));
                        }
                    }
                }
            }
            Iterator<Room> iter = rooms.iterator();
            while (iter.hasNext()) {
                Room room = iter.next();
                if (room.getNumber() == Short.parseShort("0")) {
                    iter.remove();
                }
            }
            order.setRoomslist(rooms);
        }
        roomDao.closeConnection();
        return orderList;
    }
    
     /**
     * Select actual orders with null cost (not approved by admin) from orders list
     *
     * @param list
     * @return
     */
    public List<UsersOrder> getUncheckedOrders() {
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        List<UsersOrder> uncheckedList = orderDao.findActualOrders();
        orderDao.closeConnection();
        Iterator<UsersOrder> iter = uncheckedList.iterator();
        while (iter.hasNext()) {
            UsersOrder order = iter.next();
            if (order.getCost() != 0) {
                iter.remove();
            }
        }
        return uncheckedList;
    }
    
    /**
     * Select all users
     * @return 
     */
    public List<User> getUsersAll() {
        MySqlUserDao userDao = daoFactory.getDao(MySqlUserDao.class);
        List<User> user = userDao.findAll();
        userDao.closeConnection();
        return user;
    }
    
    /**
     * Select all rooms
     * @return 
     */
    public List<Room> getRoomsAll() {
        MySqlRoomDao roomDao = daoFactory.getDao(MySqlRoomDao.class);
         List<Room> roomList = roomDao.findAll();
         roomDao.closeConnection();
        return roomList;
    }
    
    /**
     * Update comment to room using it's number
     * @param roomNumber
     * @param comment 
     */
    public void updateRoomsComment(short roomNumber, String comment){
        MySqlRoomDao roomDao = daoFactory.getDao(MySqlRoomDao.class);
        Room oldRoom = roomDao.findByPK(new Room(roomNumber));
        oldRoom.setComment(comment);
        roomDao.update(oldRoom);
        roomDao.closeConnection();
    }
    
    /**
     * Calculate the cost of given order
     * @param orderId order PK 
     * @param roomNumber for calculation
     * @return 
     */
    public int getOrderCost(int orderId, short roomNumber){
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        UsersOrder order = orderDao.findByPK(new UsersOrder(orderId));
        orderDao.closeConnection();
        MySqlRoomDao roomDao = daoFactory.getDao(MySqlRoomDao.class);
        Room room = roomDao.findByPK(new Room(roomNumber));
        roomDao.closeConnection();
        int price = room.getPrice();
        long difference = order.getEnddate().getTime() - order.getStartdate().getTime();
        long days = difference / (24 * 60 * 60 * 1000);
        int daysDiff = (int)days;
        int cost = price*daysDiff;
        if(daysDiff>7 && !room.getClass1().getName().equals("Эконом")){
            cost = (int)(cost*0.8);
        }
        return cost;
    }
    
    /**
     * Update order with roomnumber and total cost
     * @param orderId
     * @param roomNumber
     * @param cost 
     */
    public void updateOrdersRoomnumberAndCost(int orderId, short roomNumber, int cost){
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        UsersOrder order = orderDao.findByPK(new UsersOrder(orderId));
        order.setCost(cost);
        order.setRoomnumber(roomNumber);
        orderDao.update(order);
        orderDao.closeConnection();
    }
    
    /**
     * Select all orders of given user in JSON format
     * @param email
     * @return 
     */
    public String getJsonUsersOrdersCollection(String email) {
        String json = "";
        MySqlOrderDao orderDao = daoFactory.getDao(MySqlOrderDao.class);
        List<UsersOrder> list = orderDao.findUsersOrders(new User(email));
        orderDao.closeConnection();
        if (!list.isEmpty() && list != null) {
            json = "[";
            for (UsersOrder order : list) {
                json += order.toJson();
                json += ",";
            }
            int i = json.lastIndexOf(',');
            json = json.substring(0, i);
            json += "]";
        }
        log.info(json);
        return json;
    }
}
