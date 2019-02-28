
package com.sdigitizers.utils.model.money;

/**
 *
 * @author Shriram Prajapat
 */
public enum PaymentTerms {
    INSTANT(0), TOPAY(1);
    
    private final int code;
    private PaymentTerms(int code){
       this.code = code;
    }
   
    public int getCode(){
        return code;
    }    
    
    public static PaymentTerms valueOf(int code){
        switch(code){
            case 0 : return INSTANT;
            case 1 : return TOPAY;
            default : return INSTANT;
        }
    }

}
