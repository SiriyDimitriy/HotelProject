/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.command;

import java.io.IOException;
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
public class CheckAdminCommand extends AbstractCommand {

    private static Logger log = Logger.getLogger(CheckAdminCommand.class);

    public CheckAdminCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session) {
        super(request, response, session);
    }

    @Override
    public void execute() throws ServletException, IOException {
        log.info("We are in check");
        if (request.getParameter("submitted") != null) {
            log.info("Form data not null");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (handler.isAdmin(login, password)) {
                log.info("Admin check true");
                session.setAttribute("admin", "true");
                request.getRequestDispatcher("adminorders").forward(request, response);
            } else {
                log.info("Not admin");
                request.setAttribute("auth", "wrong");
                request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(request, response);
            }
        } else {
            log.info("Form data null");
            request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(request, response);
        }
    }
}
