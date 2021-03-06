
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Response;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shriram Prajapat
 */
public class TransportValidation {
    
    
    public static Response isDrivingLicenseNo(String dlNo){
            if(dlNo == null){
                return new Response(false, "License number cannot be null");
            }
            if(dlNo.isEmpty()){
                return new Response(false, "License number cannot be empty");
            }
            if(dlNo.length()<15){
                return new Response(false, "Invalid License number");
            }
            if(!dlNo.substring(0, 2).matches("[A-Za-z]*")){
                return new Response(false, "Invalid State Code In License Number");
            }
            if(!dlNo.substring(2, 4).matches("[0-9]*")){
                return new Response(false, "Invalid RTO Code In License Number");
            }
            if(!dlNo.substring(4, 8).matches("[20((1[1-9])|([2-9][0-9]))]*")){
                return new Response(false, "Invalid Year In License Number");
            }
            if(!dlNo.substring(8).matches("[0-9]*")){
                return new Response(false, "Invalid Number Series In License Number");
            }
            
            return new Response(true, "Valid License Number");
    }
    
    
    /**
     * Checks if the string is a valid Vehicle Registration (Indian Vehicle)
     * @param rcNo String to be checked
     * @return - if the string is a valid vehicle number else false
     */
    public static Response isRCNo(String rcNo){
        
            if(rcNo == null){
                return new Response(false, "Registration number cannot be null");
            }
            if(rcNo.isEmpty()){
                return new Response(false, "Registration number cannot be empty");
            }
            if(rcNo.length()<9){
                return new Response(false, "Invalid Registration number (min length should be 9)");
            }
            if(rcNo.length()>11){
                return new Response(false, "Invalid Registration number (max length can be 11)");
            }
            if(!rcNo.substring(0, 2).matches("[A-Za-z]*")){
                return new Response(false, "Invalid State Code In Regstration Number");
            }
            if(!rcNo.substring(2, 4).matches("[0-9]*")){
                return new Response(false, "Invalid RTO Code In Regstration Number");
            }
            
            //Pattern pattern = Pattern.compile("[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{1,2}\\s[0-9]{4}");
            Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}");
            Matcher m = pattern.matcher(rcNo);
            boolean b = (m.matches());
            if(b){
                return new Response(true, "Valid RC Number");
            }
            return new Response(false, "Invalid RC Number");
    }
    
    
}
