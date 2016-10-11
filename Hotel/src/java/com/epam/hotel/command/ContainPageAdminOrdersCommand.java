/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.command;

import com.epam.hotel.model.UsersOrder;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Concrete command from Command pattern
 *
 * @author Alex
 */
public class ContainPageAdminOrdersCommand extends AbstractCommand {

    private static Logger log = Logger.getLogger(ContainPageAdminOrdersCommand.class);

    public ContainPageAdminOrdersCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session) {
        super(request, response, session);
    }

    @Override
    public void execute() throws ServletException, IOException {
        if (session.getAttribute("admin") == "true") {
            List<UsersOrder> orders = handler.getActualOrdersWithAvailableRoomsList();
            List<UsersOrder> unchecked = handler.getUncheckedOrders();
            request.setAttribute("orders", orders);
            session.setAttribute("unchecked", unchecked);
            response.setHeader("Cache-Control", "no-cache");
            request.getRequestDispatcher("/WEB-INF/views/admin/adminorders.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/error/403.jsp").forward(request, response);
        }
    }
}
