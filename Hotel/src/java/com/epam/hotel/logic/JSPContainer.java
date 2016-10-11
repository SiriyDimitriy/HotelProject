/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.logic;

import com.epam.hotel.dao.MySqlClassDao;
import com.epam.hotel.daofactory.MySqlDaoFactory;
import com.epam.hotel.dao.MySqlRoomDao;
import com.epam.hotel.model.RoomsClass;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Directly contain JSP pages with database information
 *
 * @author Alex
 */
public class JSPContainer {

    private static Logger log = Logger.getLogger(JSPContainer.class);
    private static MySqlDaoFactory daoFactory = MySqlDaoFactory.instance();

    /**
     * Contain index.jsp select classes
     *
     * @return
     */
    public List<RoomsClass> fillOrderFormWithClass() {
        log.info("we are in method fillForm");
        MySqlClassDao classDao = daoFactory.getDao(MySqlClassDao.class);
        if (classDao == null) {
            log.info("classDao is null");
        }
        try {
            List<RoomsClass> roomClasses = classDao.findAll();
            classDao.closeConnection();
            if (roomClasses == null) {
                log.info("roomClasses is null");
            }
            return roomClasses;
        } catch (NullPointerException e) {
            log.error("fillOrderFormWithClass() calls null MySqlClassDao");
            log.info("Check MySqlFactory or calling to it or properly creating Factory");
            return null;
        }
    }

    /**
     * Contain index.jsp select places
     *
     * @return
     */
    public List<Integer> fillOrderFormWithPlaces() {
        try {
            MySqlRoomDao roomDao = daoFactory.getDao(MySqlRoomDao.class);
            List<Integer> places = roomDao.findAvailablePlaces();
            roomDao.closeConnection();
            return places;
        } catch (NullPointerException e) {
            log.error("fillOrderFormWithPlaces() calls null MySqlRoomDao");
            log.info("Check MySqlFactory or calling to it");
            return null;
        }
    }
}
