/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.apache.log4j.Logger;

/**
 * Class contain boolean methods checking correctness of input data
 * using special defined constant patterns or special criterias
 * @author Alex
 */
public class Validator {
    
    private static Logger log = Logger.getLogger(Validator.class);
   
    private Matcher matcher;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zа-яА-Я\\s]+$");
    private static final Pattern TEL_PATTERN = Pattern.compile("^\\+[0-9]{12}$");
 
    public Validator() {
  
    }
 
    /**
     * Validate input email using pattern EMAIL_PATTERN
     * @param hex
     * @return 
     */
    public boolean emailValidate(final String hex) {
        matcher = EMAIL_PATTERN.matcher(hex);
        return matcher.matches();
    }
    
     /**
     * Validate input string using pattern NAME_PATTERN
     * @param hex
     * @return 
     */
    public boolean nameValidate(final String hex) {
        matcher = NAME_PATTERN.matcher(hex);
        return matcher.matches();
    }
    
     /**
     * Validate input string using pattern TEL_PATTERN
     * @param hex
     * @return 
     */
    public boolean telephoneValidate(final String hex) {
        matcher = TEL_PATTERN.matcher(hex);
        return matcher.matches();
    }  
    
     /**
     * Validate input date as real date
     * @param hex
     * @return 
     */
    public boolean dateValidate(final String hex) {
        return isValidDate(hex);
    } 
    
     /**
     * Validate input dates order
     * @param date1
     * @param date2
     * @return true if first date is early then second 
     * and both dates are later then current date
     */
    public boolean dateOrderValidate(final String date1, final String date2) {
        log.info("we are in dateorderValidate");
        Date curDate = new Date();
        if(isValidDate(date1) && isValidDate(date2)){
            log.info("dates are correct");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try{
                int orderCompare = dateFormat.parse(date1).compareTo(dateFormat.parse(date2));
                int currCompare1 = curDate.compareTo(dateFormat.parse(date1));
                int currCompare2 = curDate.compareTo(dateFormat.parse(date2));
                if(orderCompare == 1 || currCompare1 == 1 || currCompare2 == 1){
                    return false;
                }else return true;        
            }catch(ParseException ex){
                log.info("Parse exveption");
                return false;
            }
        }
        return false;
    } 
    
    /**
     * Checking date format
     * @param date
     * @return 
     */
    private static boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return dateFormat.format(dateFormat.parse(date)).equals(date);
        }catch (ParseException ex){
            return false;
        }
    }
}
