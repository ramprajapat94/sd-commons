
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Response;
import java.util.regex.Pattern;

/**
 *
 * @author Shriram Prajapat
 */
public class EmailValidation {


    /**
     * Verify if an email-id is valid
     * @param email
     * @return true / false [with reason]
     */
    public static Response isValidEmailId(String email) { 
        if(null == email){
            return new Response(false, "Email cannot be null");
        }
        if(email.isEmpty()){
            return new Response(false, "Email cannot be empty");
        }
        
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailRegex); 
        
        if(pat.matcher(email).matches()){
            return new Response(true, "Valid Email-Id");
        }else{
            
            if(email.contains(" ")){
                return new Response(false, "Cannot contain space");
                
            }else{
                String specialCharacters="!#$%&'()*+/:;<=>?[]^`{|}~";
                String str2[]=email.split("");

                for (String str21 : str2) {
                    if (specialCharacters.contains(str21)) {
                        return new Response(false, "Includes invalid character(s)");
                    }
                }
                
                if(!email.contains("@")){
                    return new Response(false, "Provider Domain is missing");
                }else
                if(!email.contains(".")){
                    return new Response(false, "Domain is missing");
                }else
                if(email.lastIndexOf("@") > email.lastIndexOf(".")){
                    return new Response(false, "Valid Domain is missing");
                }else
                if(email.lastIndexOf(".") < email.length()+2){
                    return new Response(false, "Valid Domain is missing.");
                }else{
                    return new Response(false, "Includes Email Id");
                }
            }
        }
    } 
    
}
