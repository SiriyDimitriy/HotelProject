/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.daofactory;

import com.epam.hotel.dao.MySqlAdminDao;
import com.epam.hotel.dao.MySqlClassDao;
import com.epam.hotel.dao.MySqlOrderDao;
import com.epam.hotel.dao.MySqlRoomDao;
import com.epam.hotel.dao.MySqlUserDao;
import com.epam.hotel.daofactory.IDaoFactory;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Factory for dao's
 *
 * @author Alex
 */
public class MySqlDaoFactory implements IDaoFactory {

    private static MySqlDaoFactory uniqueInstance;
    private static Logger log = Logger.getLogger(MySqlDaoFactory.class);
    private Set<Class> daoSet;

    private MySqlDaoFactory() {
        daoSet = new HashSet<Class>();
        daoSet.add(MySqlUserDao.class);
        daoSet.add(MySqlClassDao.class);
        daoSet.add(MySqlRoomDao.class);
        daoSet.add(MySqlOrderDao.class);
        daoSet.add(MySqlAdminDao.class);
    }

    public static MySqlDaoFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MySqlDaoFactory();
        }
        return uniqueInstance;
    }

    private Connection makeConnection() {
        return ConnectionPool.getConnection();
    }

    public <T> T getDao(Class daoClass) {
        log.info("we are in getDa0");
        T dao = null;
        if (daoClass != null) {
            if (daoSet.contains(daoClass)) {
                try {
                    dao = (T) daoClass.getConstructor(Connection.class).newInstance(makeConnection());
                } catch (Exception e) {
                    log.fatal("there is no such dao in the Set");
                }
            }
        } else {
            log.fatal("there is no such class at all");
        }
        log.info(dao.getClass().getName());
        return dao;
    }
}
