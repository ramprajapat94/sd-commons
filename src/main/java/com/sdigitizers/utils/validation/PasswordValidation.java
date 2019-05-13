
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Pair;

/**
 *
 * @author Shriram Prajapat
 */
public class PasswordValidation {
    
    /**
     * Low level of validation on password [Length 8-16]
     * @param password
     * @return 
     */
    public static Pair<Boolean, String> validateLow(String password) {
        if(null == password){
            return new Pair<>(false, "Password cannot be null");
        }
        if(password.isEmpty()){
            return new Pair<>(false, "Password cannot be empty");
        }
        if (password.length() < 8) {
            return new Pair<>(false, "Password must have length of atleast 8");
        }
        if (password.length() > 16) {
            return new Pair<>(false, "Password can have max length of 16");
        }
        return new Pair<>(true, "Valid Password");
    }
    
    /**
     * Medium Level of authentication [Length 8-16, AlphaNumeric]
     * @param password
     * @return 
     */
    public static Pair<Boolean, String> validateMedium(String password) {
        if(null == password){
            return new Pair<>(false, "Password cannot be null");
        }
        if(password.isEmpty()){
            return new Pair<>(false, "Password cannot be empty");
        }
        if (password.length() < 8) {
            return new Pair<>(false, "Password must have length of atleast 8");
        }
        if (password.length() > 16) {
            return new Pair<>(false, "Password can have max length of 16");
        }
        
        boolean alpha = false;
            for (char x : password.toCharArray()) {
                if (Character.isLetter(x)) {
                    if (Character.isLetter(x)) {
                        alpha = true;
                        break;
                    }
                }
            }
        boolean digit = false;
            for (char x : password.toCharArray()) {
                if (Character.isLetter(x)) {
                    if (Character.isDigit(x)) {
                        digit = true;
                        break;
                    }
                }
            }
            
        if (!alpha) {
            return new Pair<>(false, "Password must contain a letter");
        }
        
        if (!digit) {
            return new Pair<>(false, "Password must contain a number");
        }
            
        return new Pair<>(true, "Valid Password");
    }

    
    /**
     * High Level of authentication [Length 8-16, AlphaNumeric, Spcl Character & UpperCase-LowerCase]
     * @param password
     * @return 
     */
    public static Pair<Boolean, String> validateHigh(String password) {
        if(null == password){
            return new Pair<>(false, "Password cannot be null");
        }
        if(password.isEmpty()){
            return new Pair<>(false, "Password cannot be empty");
        }
        if (password.length() < 8) {
            return new Pair<>(false, "Password must have length of atleast 8");
        }
        if (password.length() > 16) {
            return new Pair<>(false, "Password can have max length of 16");
        }
        
        boolean alpha = false;
            for (char x : password.toCharArray()) {
                if (Character.isLetter(x)) {
                    if (Character.isAlphabetic(x)) {
                        alpha = true;
                        break;
                    }
                }
            }
            
        if (!alpha) {
            return new Pair<>(false, "Password must contain a letter");
        }
        
        boolean digit = false;
            for (char x : password.toCharArray()) {
                if (Character.isLetter(x)) {
                    if (Character.isDigit(x)) {
                        digit = true;
                        break;
                    }
                }
            }
            
        if (!digit) {
            return new Pair<>(false, "Password must contain a number");
        }
         
        boolean upperCase = false;
            for (char x : password.toCharArray()) {
                if (Character.isLetter(x)) {
                    if (Character.isUpperCase(x)) {
                        digit = true;
                        break;
                    }
                }
            }
            
        if (!upperCase) {
            return new Pair<>(false, "Password must contain a Uppercase Letter");
        }   
        
        boolean lowerCase = false;
            for (char x : password.toCharArray()) {
                if (Character.isLetter(x)) {
                    if (Character.isLowerCase(x)) {
                        digit = true;
                        break;
                    }
                }
            }
            
        if (!lowerCase) {
            return new Pair<>(false, "Password must contain a Lowercase Letter");
        }   
        
        return new Pair<>(true, "Valid Password");
    }
    
}
