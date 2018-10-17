
package com.sdigitizers.utils.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * General Validation Methods
 * @author Shriram Prajapat
 */
public class Validation {

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
     * To validate if the string is a valid phone number
     * @param s String to be validated
     * @return true - if valid phone number else false
     */
    public static boolean isPhoneNo(String s) {
        return s != null && s.length() == 10 && isNumericPositive(s);
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
     * To validate if the string is a valid email id
     * @param s String to be validated
     * @return true - if valid email id else false
     */
    public static boolean isMailID(String s) {
        int idx = s.indexOf("@");
        int idxo = s.lastIndexOf(".");
        return ((s.equals("") || idx < 1 || idxo < idx + 2) == false) && !s.contains("'");
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
        
//        /**
//     * To check if the string is only numeric (contains only numbers)
//     * @param s String to be checked
//     * @return true - if the string is contains only numbers else false
//     */
//    public static boolean isNumericPositive(String s) {
//        if (s != null) {
//           try{
//                Long.parseLong(s); return true;
//           }catch(NumberFormatException ex){return false;}
//        } else {
//            return false;
//        }
//    }

    
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
     * Checks if the string is a valid bank account number (Indian bank accounts)
     * @param s String to be checked
     * @return - if the string is a valid bank account number else false
     */
    public static boolean isBankAccountNo(String s){
         return (s!=null && s.length()>=4 && s.length()<=17 && isNumericPositive(s));
    }

    
    /**
     * Checks if the string is a valid PAN (Indian PAN)
     * @param s String to be checked
     * @return - if the string is a valid PAN else false
     */
    public static boolean isPAN(String s){
         Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
         Matcher m = pattern.matcher(s);
         return (s!=null && s.length()>=10 && s.length()<=10 && m.matches());
    }

    
    /**
     * Checks if the string is a valid IFSC (Indian IFSC)
     * @param s String to be checked
     * @return - if the string is a valid IFSC else false
     */
    public static boolean isIFSC_Code(String s){
         return (s!=null && s.length()>=11 && s.length()<=11);
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
        if (s != null) {
            return (s.startsWith("http://") || s.startsWith("https://")) && s.contains(".");
        } else {
            return false;
        }
    }
    
    /**
     * Checks if the string is a valid Vehicle Registration (Indian Vehicle)
     * @param s String to be checked
     * @return - if the string is a valid vehicle number else false
     */
    public static boolean isVehicleNumber(String s){
         Pattern pattern = Pattern.compile("[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{1,2}\\s[0-9]{4}");
         Matcher m = pattern.matcher(s);
         return (s!=null && m.matches());
    }
    
    public static class Field{

        public static boolean isID(String s){
            return ( s!=null && s.length()>=5 && s.length()<=12 );
        }
        
        public static boolean isEmployeeID(String s){
        Pattern pattern = Pattern.compile("[A-Z]{2}[-]{1}[0-9]{4}");
        Matcher m = pattern.matcher(s);
        return (s!=null && s.length()>=5 && s.length()<=10)  && m.matches();
        }
        
        public static boolean isStudentID(String s){
            //Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}");
            //Matcher m = pattern.matcher(s);
            return (s!=null && s.length()>=12 && s.length()<=12); //&& m.matches();
        }
        public static boolean isFirstName(String s){
            return (s!=null && s.length()>=2 && s.length()<=40);
        }
        public static boolean isMiddleName(String s){
            return (s!=null && s.length()>=2 && s.length()<=40);
        }
        public static boolean isLastName(String s){
             return (s!=null && s.length()>=2 && s.length()<=40);
        }
        public static boolean isFullName(String s){
            return (s!=null && s.length()>=2 && s.length()<=100 );
        }
        public static boolean isUserName(String s){
             return (s!=null && s.length()>=2 && s.length()<=30 && isNoCharacterPresent(s));
        }
        public static boolean isPassword(String s){
             return (s!=null && s.length()>=8 && s.length()<=16);
        }
        public static boolean isDepartment(String s){
             return (s!=null && s.length()>=4 && s.length()<=15);
        }
        public static boolean isJobTitle(String s){
             return (s!=null && s.length()>=5 && s.length()<=50);
        }
        public static boolean isSpecific1(String s){
             return (s!=null && s.length()>=2 && s.length()<=50);
        }
        public static boolean isOccupation(String s){
             return (s!=null && s.length()>=5 && s.length()<=70);
        }
        public static boolean isStreet(String s){
             return (s!=null && s.length()>=2 && s.length()<=50);
        }
        public static boolean isCity(String s){
             return (s!=null && s.length()>=2 && s.length()<=30);
        }
        public static boolean isPincode(String s){
             return (s!=null && s.length()>=5 && s.length()<=8);
        }
        public static boolean isState(String s){
             return (s!=null && s.length()>=30 && s.length()<=30);
        }
        public static boolean isDistrict(String s){
             return (s!=null && s.length()>=2 && s.length()<=30);
        }
        public static boolean isCountry(String s){
             return (s!=null && s.length()>=2 && s.length()<=20);
        }
        public static boolean isHighestDegreeEarned(String s){
             return (s!=null && s.length()>=0 && s.length()<=50);
        }
        public static boolean isCourseDescription(String s){
             return (s!=null && s.length()>=0 && s.length()<=500);
        }
        public static boolean isWorkExperienceDesc(String s){
             return (s!=null && s.length()>=0 && s.length()<=1000);
        }
        public static boolean isCertificatesOwn(String s){
             return (s!=null && s.length()>=0 && s.length()<=1000);
        }
        public static boolean isResearch(String s){
             return (s!=null && s.length()>=0 && s.length()<=1000);
        }
        public static boolean isWebProfile(String s){
             return (s!=null && s.length()>=0 && s.length()<=300 && (s.startsWith("http://") || s.startsWith("https://")));
        }
        public static boolean isSkills(String s){
             return (s!=null && s.length()>=0 && s.length()<=300);
        }
        public static boolean isStatusMessage(String s){
             return (s!=null && s.length()>=1 && s.length()<=50);
        }
        public static boolean isChatMessage(String s){
             return (s!=null && s.length()>=1 && s.length()<=500);
        }
        public static boolean isReminderText(String s){
             return (s!=null && s.length()>=1 && s.length()<=500);
        }
        public static boolean isShortDescription(String s, byte minLength){
            return (s!=null && s.length()>=minLength && s.length()<=200 );
        }

        public static boolean isMediumDescription(String s, byte minLength){
            return (s!=null && s.length()>=minLength && s.length()<=500 );
        }

        public static boolean isLongDescription(String s, byte minLength){
            return (s!=null && s.length()>=minLength && s.length()<=1000 );
        }
         public static boolean isSchool(String s){
            return (s!=null && s.length()>=3 && s.length()<=3 && isNoCharacterPresent(s));
        }
        public static boolean isSchoolName(String s){
            return (s!=null && s.length()>=5 && s.length()<=50);
        }

    
        public static boolean isSubjectCode(String s){
            return (s!=null && s.length()>=5 && s.length()<=15);
        }

        public static boolean isSubjectShortName(String s){
            return (s!=null && s.length()>=2 && s.length()<=6);
        }
        public static boolean isProgramName(String s){
            return (s!=null && s.length()>=10 && s.length()<=30);
        }
        public static boolean isBranch(String s){
            return (s!=null && s.length()>=20 && s.length()<=40);
        }
        public static boolean isBranchTitle(String s){
             return (s!=null && s.length()>=20 && s.length()<=100);
        }
        public static boolean isBranchCode(String s){
             return (s!=null && s.length()>=10 && s.length()<=20);
        }
        public static boolean isBlock(String s){
             return (s!=null && s.length()>=5 && s.length()<=20);
        }
        public static boolean isFloor(String s){
             return isNumericPositive(s);
        }
        public static boolean isRoomDetail(String s){
             return (s!=null && s.length()>=5 && s.length()<=50);
        }
    }

       
}
