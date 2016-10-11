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
public class ExitAdminSessionCommand extends AbstractCommand {

    private static Logger log = Logger.getLogger(ExitAdminSessionCommand.class);

    public ExitAdminSessionCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session) {
        super(request, response, session);
    }

    @Override
    public void execute() throws ServletException, IOException {
        session.removeAttribute("admin");
        session.invalidate();
        request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(request, response);
    }
}
