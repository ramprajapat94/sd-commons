
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Response;

/**
 *
 * @author Shriram Prajapat
 */
public class PhoneValidation {
    
    /**
     * Verify if a number is correct
     * @param number
     * @return true if number is correct, otherwise false (with reason/description)
     */
    public static Response isValidNumber(String number) {
        return isValidNumber("91", number);
    }
//        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
//        Matcher m = p.matcher(s); 
//        return (m.find() && m.group().equals(s)); 
    
     /**
     * Verify if a number is correct
     * @param countryCode
     * @param number
     * @return true if number is correct, otherwise false
     */
    public static Response isValidNumber(String countryCode, String number) {
        if( number == null ){
            return new Response(false, "Phone number cannot be null");
        }
        if( number.length() != 10 ){
            return new Response(false, "Phone number must be of 10 digits");
        }
        try{
             long l = Long.parseLong(number);
             if(l < 0){
                return new Response(false, "Phone number cannot be negative"); 
             }
             if(countryCode.equals("91")){
                 if(Integer.parseInt(number.charAt(0)+"")<5){
                     return new Response(false, "Invalid series number"); 
                 }
             }
        }catch(NumberFormatException ex){
            return new Response(false, "Phone number must contain only numbers");
        }
       return new Response(true, "Valid phone number");
    }
    
}
