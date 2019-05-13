
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Pair;

/**
 *
 * @author Shriram Prajapat
 */
public class Validation {
    
    ///PHONE
    public static Pair<Boolean, String> isPhone(String number){
        return PhoneValidation.isValidNumber(number);
    }
    
    public static Pair<Boolean, String> isPhone(String countryCode, String number){
        return PhoneValidation.isValidNumber(countryCode, number);
    }
    
    
    ////EMAIL
    public static Pair<Boolean, String> isEmailId(String email){
        return EmailValidation.isValidEmailId(email);
    }
    
    ///PASSWORD
    public static Pair<Boolean, String> isPasswordLow(String pass){
        return PasswordValidation.validateLow(pass);
    }
    
    public static Pair<Boolean, String> isPasswordMedium(String pass){
        return PasswordValidation.validateMedium(pass);
    }
    
    public static Pair<Boolean, String> isPasswordHigh(String pass){
        return PasswordValidation.validateHigh(pass);
    }
    
    
    ////TRANSPORT
    public static Pair<Boolean, String> isDrivingLicenseNo(String dlNo){
        return TransportValidation.isDrivingLicenseNo(dlNo);
    }
    
    public static Pair<Boolean, String> isRCNo(String rcNo){
        return TransportValidation.isRCNo(rcNo);
    }
    
    
    //// BUSINESS
    public static Pair<Boolean, String> isBankAccNo(String accNo){
        return BusinessValidation.isValidAccountNumber(accNo);
    }
    
    public static Pair<Boolean, String> isIFSC(String ifsc){
        return BusinessValidation.isValidIFSC(ifsc);
    }
    
    public static Pair<Boolean, String> isPAN(String pan){
        return BusinessValidation.isPAN(pan);
    }

    /**
     * Verify if the given GSTIN is correct
     * @param gstin The GST identification Number (GST-IN)
     * @return true if a valid GSTIN, else false
     */
    public static boolean isGSTIN(String gstin) {
        return GSTINValidation.isValidGSTIN(gstin);
    }
}
