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
public class SetLanguageCommand extends AbstractCommand {

    private static Logger log = Logger.getLogger(SetLanguageCommand.class);

    public SetLanguageCommand(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) {
        super(request, response, session);
    }

    @Override
    public void execute() throws ServletException, IOException {
        if (request.getParameter("ru") != null) {
            session.setAttribute("lang", "ru");
            session.setAttribute("resource", "com/epam/hotel/resources/ru");
        }
        if (request.getParameter("en") != null) {
            session.setAttribute("resource", "com/epam/hotel/resources/en");
            session.setAttribute("lang", "en");
        }
        request.getRequestDispatcher(request.getParameter("path")).forward(request, response);
    }
}

