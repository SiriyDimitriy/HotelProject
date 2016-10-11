/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.command;

import com.epam.hotel.logic.DaoHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class Command from Command pattern
 * @author Alex
 */
public abstract class AbstractCommand{
    protected DaoHandler handler;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    
    public AbstractCommand(HttpServletRequest request, 
            HttpServletResponse response, HttpSession session){
        this.handler = new DaoHandler();
        this.request = request;
        this.response = response;
        this.session = session;
    }
    
    public AbstractCommand(HttpServletRequest request, 
            HttpServletResponse response){
        this.handler = new DaoHandler();
        this.request = request;
        this.response = response;
    }
    
    public abstract void execute() throws ServletException, IOException;
}
