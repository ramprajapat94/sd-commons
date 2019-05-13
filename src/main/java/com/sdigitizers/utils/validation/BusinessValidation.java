package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Pair;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shriram Prajapat
 */
public class BusinessValidation {

    public static Pair<Boolean, String> isValidAccountNumber(String s) {
        if (null == s) {
            return new Pair<>(false, "Account Number cannot be null");
        }
        if (s.isEmpty()) {
            return new Pair<>(false, "Account Number cannot be empty");
        }
        if (s.length() < 8) {
            return new Pair<>(false, "Account Number too small");
        }
        if (s.length() > 30) {
            return new Pair<>(false, "Account Number too long");
        }
        if (!s.matches("[0-9]*")) {
            return new Pair<>(false, "Invalid Account Number");
        }

        return new Pair<>(true, "Valid Account Number");
    }

    public static Pair<Boolean, String> isValidIFSC(String ifsc) {
        if (ifsc == null) {
            return new Pair<>(false, "IFSC cannot be null");
        }
        if (ifsc.isEmpty()) {
            return new Pair<>(false, "IFSC number cannot be empty");
        }
        if (ifsc.length() != 11) {
            return new Pair<>(false, "Invalid IFSC length (length should be 11)");
        }
        if (!ifsc.substring(0, 4).matches("[A-Za-z]*")) {
            return new Pair<>(false, "Invalid Bank Code In IFSC");
        }
//        if (!ifsc.substring(4).matches("[0-9]*")) {
//            return new Pair<>(false, "Invalid Branch Code in IFSC (should be only numeric)");
//        }

        return new Pair<>(true, "Valid IFSC");
    }
    
    public static Pair<Boolean, String> isPAN(String s){
         Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
         Matcher m = pattern.matcher(s);
         boolean b = (s!=null && s.length()>=10 && s.length()<=10 && m.matches());
         if(b){
             return new Pair<>(b, "Valid PAN");
         }else{
             return new Pair<>(b, "Invalid PAN");
         }
    }

}
