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

/**
 * Concrete command from Command pattern
 * @author Alex
 */
public class ContainPagePricesCommand extends AbstractCommand{

    public ContainPagePricesCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session){
        super(request, response, session);
    }
    
    @Override
    public void execute() throws ServletException, IOException{
        request.setAttribute("room", handler.getRoomsPriceList());
        request.getRequestDispatcher("/WEB-INF/views/main/prices.jsp").forward(request, response);
    }
}
