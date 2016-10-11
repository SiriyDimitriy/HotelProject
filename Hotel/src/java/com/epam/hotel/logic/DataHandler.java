/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.logic;

import com.epam.hotel.model.UsersOrder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Data manipulating class of static methods 
 * Filtering collections, validate data
 * @author Alex
 */
public class DataHandler {

    private static Logger log = Logger.getLogger(DataHandler.class);

    /**
     * Checking map of order data on correctness, triming
     *
     * @param formParameters
     * @return HashSet of string errors
     */
    public static HashSet<String> validateOrderForm(Map<String, String> formParameters) {
        final String DATE_ERROR = "dateError";
        final String DATE_ORDER_ERROR = "dateOrderError";

        Validator val = new Validator();
        HashSet<String> errors = new HashSet<String>();

        for (String str : formParameters.values()) {
            str = str.trim();
            log.info("Trimed data from request");
            log.info(str);
        }

        if (!((val.dateValidate(formParameters.get("dateFrom"))) && (val.dateValidate(formParameters.get("dateTo"))))) {
            errors.add(DATE_ERROR);
        } else if (!val.dateOrderValidate(formParameters.get("dateFrom"), formParameters.get("dateTo"))) {
            errors.add(DATE_ORDER_ERROR);
        }
        return errors;
    }

    /**
     * Checking map of user data on correctness, triming
     *
     * @param formParameters
     * @return HashSet of string errors
     */
    public static HashSet<String> validateUserForm(Map<String, String> formParameters) {
        final String NAME_ERROR = "nameError";
        final String EMAIL_ERROR = "emailError";
        final String TELEPHONE_ERROR = "telephoneError";
        Validator val = new Validator();
        HashSet<String> errors = new HashSet<String>();

        for (String str : formParameters.values()) {
            str = str.trim();
            log.info("Trimed data from request");
            log.info(str);
        }

        if (!val.nameValidate(formParameters.get("name"))) {
            errors.add(NAME_ERROR);
        }
        if (!val.emailValidate(formParameters.get("email"))) {
            errors.add(EMAIL_ERROR);
        }
        if (!val.telephoneValidate(formParameters.get("telephone"))) {
            errors.add(TELEPHONE_ERROR);
        }
        return errors;
    }
}
