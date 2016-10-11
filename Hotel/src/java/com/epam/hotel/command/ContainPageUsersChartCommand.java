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
public class ContainPageUsersChartCommand extends AbstractCommand {

    private static Logger log = Logger.getLogger(ContainPageUsersChartCommand.class);

    public ContainPageUsersChartCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session) {
        super(request, response, session);
    }

    @Override
    public void execute() throws ServletException, IOException {
        if (request.getParameter("deleted") != null) {
            List<UsersOrder> orders = (List<UsersOrder>) session.getAttribute("chart");
            UsersOrder deletedOrder = handler.deleteOrder(
                    Integer.parseInt(request.getParameter("id")));
            orders.remove(deletedOrder);
            session.setAttribute("chart", orders);
        }
        request.getRequestDispatcher("/WEB-INF/views/main/chart.jsp").forward(request, response);
    }
}
