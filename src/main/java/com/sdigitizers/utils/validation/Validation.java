
package com.sdigitizers.utils.validation;

import com.sdigitizers.utils.util.Response;


/**
 *
 * @author Shriram Prajapat
 */
public class Validation {
    
    ///PHONE
    public static Response isPhone(String number){
        return PhoneValidation.isValidNumber(number);
    }
    
    public static Response isPhone(String countryCode, String number){
        return PhoneValidation.isValidNumber(countryCode, number);
    }
    
    
    ////EMAIL
    public static Response isEmailId(String email){
        return EmailValidation.isValidEmailId(email);
    }
    
    ///PASSWORD
    public static Response isPasswordLow(String pass){
        return PasswordValidation.validateLow(pass);
    }
    
    public static Response isPasswordMedium(String pass){
        return PasswordValidation.validateMedium(pass);
    }
    
    public static Response isPasswordHigh(String pass){
        return PasswordValidation.validateHigh(pass);
    }
    
    
    ////TRANSPORT
    public static Response isDrivingLicenseNo(String dlNo){
        return TransportValidation.isDrivingLicenseNo(dlNo);
    }
    
    public static Response isRCNo(String rcNo){
        return TransportValidation.isRCNo(rcNo);
    }
    
    
    //// BUSINESS
    public static Response isBankAccNo(String accNo){
        return BusinessValidation.isValidAccountNumber(accNo);
    }
    
    public static Response isIFSC(String ifsc){
        return BusinessValidation.isValidIFSC(ifsc);
    }
    
    public static Response isPAN(String pan){
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
