/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.command;

import com.epam.hotel.logic.DataHandler;
import com.epam.hotel.model.User;
import com.epam.hotel.model.UsersOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
public class ProcessingOrderFormCommand extends AbstractCommand{

    private static Logger log = Logger.getLogger(ProcessingOrderFormCommand.class);

    public ProcessingOrderFormCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session) {
        super(request, response, session);
    }

    @Override
    public void execute() throws ServletException, IOException{
        if (request.getParameter("submitted") != null) {
            Map<String, String> formParameters = new HashMap();
            User user = (User) session.getAttribute("user");
            HashSet<String> userErrors = new HashSet<String>();
            HashSet<String> orderErrors = new HashSet<String>();
            if (user == null) {
                formParameters.put("name", request.getParameter("name"));
                formParameters.put("email", request.getParameter("email"));
                formParameters.put("telephone", request.getParameter("telephone"));
                userErrors = DataHandler.validateUserForm(formParameters);
            } else {
                formParameters.put("name", user.getName());
                formParameters.put("email", user.getEmail());
                formParameters.put("telephone", user.getTelephone());
            }
            formParameters.put("dateFrom", request.getParameter("dateFrom"));
            formParameters.put("dateTo", request.getParameter("dateTo"));
            orderErrors = DataHandler.validateOrderForm(formParameters);
            if (orderErrors.isEmpty() && userErrors.isEmpty()) {
                log.info("validation success");
                formParameters.put("places", request.getParameter("places"));
                formParameters.put("class", request.getParameter("class"));
                formParameters.put("comment", request.getParameter("comment"));
                
                List<UsersOrder> orders = (List)session.getAttribute("chart");
                if (user == null) {
                    orders = new ArrayList<UsersOrder>();
                    user = handler.createUser(formParameters);
                    session.setAttribute("user", user);
                }
                orders.add(handler.createOrder(formParameters, user));
                session.setAttribute("chart", orders);
                request.setAttribute("errors", "no");
                log.info("all happy");
            } else {
                log.info("validation failed");
                userErrors.addAll(orderErrors);
                request.setAttribute("errors", userErrors);
            }

        }
        request.getRequestDispatcher("/WEB-INF/views/main/accept.jsp").forward(request, response);
    }
}
