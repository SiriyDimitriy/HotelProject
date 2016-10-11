/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.customtag.helloadmin;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;

/**
 * Custom tag show order's number for admin 
 * @author Alex
 */
public class HelloAdminHaveOrders extends SimpleTagSupport {

    private String id;
    private String name;
    private String orders;
    private static Logger log = Logger.getLogger(HelloAdminHaveOrders.class);

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            try{
                out.print("Здравствуйте, <b>" + name + "</b>!<br>");
                out.print("У Вас неподтверждённых заказов: <b>" + orders + "</b>");
            }catch(IOException e){
                log.error("IO Exception in custom tag output");
            }
            
            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in HelloAdminHaveOrders tag", ex);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
    
}
