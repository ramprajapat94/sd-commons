
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Pair;

/**
 * General GeneralValidation Methods
 * @author Shriram Prajapat
 */
public class GeneralValidation {
    
    public static Pair<Boolean, String> isValidPincode(String pincode){
        if(null == pincode){
            return new Pair<>(false, "Pincode cannot be null");
        }
        if(!pincode.matches("[0-9]*")){
            return new Pair<>(false, "Pincode must contain only numbers");
        }
        if(pincode.length()!=6){
            return new Pair<>(false, "Pincode must be of exactly 6 numbers");
        }
        return new Pair<>(true, "SUCCESS");
    }
    
    /**
     * To validate string against presence of any special character
     * @param s String to be validated/checked
     * @return true - if no special character is present else false
     */
    public static boolean isNoCharacterPresent(String s) {
        if (s != null) {
            return !s.contains("@") && !s.contains(".") && !s.contains("!") 
                    && !s.contains("#") && !s.contains("$") && !s.contains("%") 
                    && !s.contains("&") && !s.contains("*") && !s.contains("(") 
                    && !s.contains(")") && !s.contains("-") && !s.contains("+") 
                    && !s.contains("-") && !s.contains("/") && !s.contains("[") 
                    && !s.contains("]") && !s.contains("{") && !s.contains("}") 
                    && !s.contains("'") && !s.contains(":") && !s.contains(";") 
                    && !s.contains("\\") && !s.contains(">") && !s.contains("<") 
                    && !s.contains(",") && !s.contains("?") && !s.contains("|") 
                    && !s.contains("`") && !s.contains("~") && !s.contains(" ") 
                    && !s.contains("+") && !s.contains("'");
        } else {
            return false;
        }
    }
    
    /**
     * To check if the string is alpha-numeric only (No any special character)
     * @param s String to be checked
     * @return true - if the string is only alpha-numeric
     */
    public static boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9]*$";
        return s.matches(pattern);   
    }
    
    /**
     * To check if the string is a valid file name
     * @param s String to be checked
     * @return true - if the string is valid file name else false
     */
    public static boolean isValidFileName(String s) {
        return s != null && !s.isEmpty() && s.length() < 255;
    }

    /**
     * To check if the string represents percentage (0-100)
     * @param s String to be checked
     * @return true - if the string represents valid percentage (0-100) only numbers else false
     */
    public static boolean isPercentage(String s) {
        if (s != null) {
           try{
                double p = Double.parseDouble(s);
                if(p>=0 && p<=100)return true;
           }catch(NumberFormatException ex){return false;}
        }
        return false;
    }
    
    /**
     * To check if the string is only numeric (contains only positive numbers)
     * @param s String to be checked
     * @return true - if the string is contains only numbers else false
     */
    public static boolean isNumericPositive(String s) {
        if (s != null) {
           try{
                return Long.parseLong(s) > -1;
           }catch(NumberFormatException ex){return false;}
        } else {
            return false;
        }
    }
    
        /**
     * To check if the string is only numeric (contains only numbers)
     * @param s String to be checked
     * @return true - if the string is contains only numbers else false
     */
    public static boolean isNumeric(String s) {
        if (s != null) {
           try{
                Long.parseLong(s); return true;
           }catch(NumberFormatException ex){return false;}
        } else {
            return false;
        }
    }

    
    /**
     * To check if the string is only numeric or decimal (contains only positive numbers with or without point values)
     * @param s String to be checked
     * @return true - if the string is contains only numbers or point values else false
     */
    public static boolean isNumericOrDecimalPositive(String s) {
        if (s != null) {
            try{
               return Double.parseDouble(s) > -1;
           }catch(NumberFormatException ex){return false;}
        } else {
            return false;
        }
    }

     /**
     * To check if the string is only numeric or decimal (contains only numbers or point values)
     * @param s String to be checked
     * @return true - if the string is contains only numbers or point values else false
     */
    public static boolean isNumericOrDecimal(String s) {
        if (s != null) {
            try{
                Double.parseDouble(s); return true;
           }catch(NumberFormatException ex){return false;}
        } else {
            return false;
        }
    }
    
    
    /**
     * To check if the string is safe against SQL query operations (*when used with statement)
     * @param s String to be checked
     * @return true - if the string is safe else false
     */
    public static boolean isSQL_SafeString(String s) {
            if (s != null) {
                return !s.contains("'");
            } else {
                return false;
            }
    }

    /**
     * Checks if the string is not null or empty
     * @param s String to be checked
     * @return true - if string has some value else false
     */
    public static boolean isValidString(String s){
            return s!=null && !s.isEmpty();
    }

    
    /**
     * https://onerupee.net/format-of-epf-account-number-in-india/
     * Checks if the string is a EPF Account Number (Indian EPF)
     * @param s String to be checked
     * @return - if the string is a valid EPF else false
     */
    public static boolean isEPFAccountNumber(String s){
         return (s!=null && s.length()>=18 && s.length()<=30);
    }
    
    /**
     * Checks if the string is a valid ESIC (Indian ESIC)
     * @param s String to be checked
     * @return - if the string is a valid ESIC else false
     */
    public static boolean isESICAccountNumber(String s){
         return (s!=null && s.length()>=15 && s.length()<=30);
    }

    /**
     * Checks if the string is a valid Extension Code (Institutional internal telephonic Code)
     * @param s String to be checked
     * @return - if the string is a valid extension code else false
     */
    public static boolean isEPABX(String s){
         return (s!=null && s.length()>=4 && s.length()<=4 && isNumericPositive(s));
    }

    /**
     * To validate if the string is a valid URL
     * @param s String to be validated/checked
     * @return true - if it is a valid URL
     */
    public static boolean isURL(String s) {
        return (isHttpURL(s) || isFileURL(s));
    }
    
    /**
     * To validate if the string is a valid HTTP URL
     * @param s String to be validated/checked
     * @return true - if it is a valid URL
     */
    public static boolean isHttpURL(String s) {
        if (s != null) {
            return (s.startsWith("http://") || s.startsWith("https://")) && s.contains(".");
        } else {
            return false;
        }
    }
    
    /**
     * To validate if the string is a valid FILE URL
     * @param s String to be validated/checked
     * @return true - if it is a valid URL
     */
    public static boolean isFileURL(String s) {
        if (s != null) {
            return (s.startsWith("file:///") && s.contains(":"));
        } else {
            return false;
        }
    }
    
    
//    public static boolean isEmployeeID(String s){
//            Pattern pattern = Pattern.compile("[A-Z]{2}[-]{1}[0-9]{4}");
//            Matcher m = pattern.matcher(s);
//            return (s!=null && s.length()>=5 && s.length()<=10)  && m.matches();
//    }
           
}
