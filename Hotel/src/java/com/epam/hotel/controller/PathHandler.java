/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controller;

import java.util.HashMap;
import com.epam.hotel.command.*;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Contain map of correspondences for url-pathes and commands processing them
 *
 * @author Alex
 */
public class PathHandler {

    private HashMap<String, Class> pathMap;
    private static Logger log = Logger.getLogger(PathHandler.class);

    public PathHandler() {
        pathMap = new HashMap<String, Class>();
        pathMap.put("/index", ShowPageIndexCommand.class);
        pathMap.put("/about", ShowPageAboutCommand.class);
        pathMap.put("/contact", ShowPageContactsCommand.class);
        pathMap.put("/discount", ShowPageDiscountCommand.class);
        pathMap.put("/rooms", ContainPageRoomsCommand.class);
        pathMap.put("/prices", ContainPagePricesCommand.class);
        pathMap.put("/accept", ProcessingOrderFormCommand.class);
        pathMap.put("/chart", ContainPageUsersChartCommand.class);
        pathMap.put("/check", CheckAdminCommand.class);
        pathMap.put("/adminorders", ContainPageAdminOrdersCommand.class);
        pathMap.put("/adminusers", ContainPageAdminUsersCommand.class);
        pathMap.put("/adminrooms", ContainPageAdminRoomsCommand.class);
        pathMap.put("/exit", ExitAdminSessionCommand.class);
        pathMap.put("/ajaxRoom", AjaxUpdateRoomsCommentCommand.class);
        pathMap.put("/ajaxSetOrder", AjaxApproveOrderCommand.class);
        pathMap.put("/ajaxDeleteOrder", AjaxDeleteOrderCommand.class);
        pathMap.put("/ajaxOrders", AjaxSelectCurrentUserOrdersCommand.class);
        pathMap.put("/language", SetLanguageCommand.class);
    }

    public void handle(String userPath, HttpServletRequest request, HttpServletResponse response,
            HttpSession session) {
        try {
            Class classObj = pathMap.get(userPath);

            AbstractCommand command = (AbstractCommand) classObj.getConstructor(HttpServletRequest.class,
                    HttpServletResponse.class, HttpSession.class).newInstance(request,
                            response, session);
            command.execute();

        }catch (IOException | ServletException e) {
            log.error("This is IO or ServlerException ");
        }catch (Exception e){
            log.error("May be your call null MySqlClassDao");
            log.info("Check MySqlFactory or calling to it or properly creating Factory");
            log.error("Reflection with command failed");
            log.info("Or may be some command make it...");
        }
    }
}
