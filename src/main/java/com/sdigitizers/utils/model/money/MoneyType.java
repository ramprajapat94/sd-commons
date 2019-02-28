
package com.sdigitizers.utils.model.money;

/**
 *
 * @author Shriram Prajapat
 */
public enum MoneyType {
    
    NA(0), HARD_MONEY(1), BANK_MONEY(2), E_WALLET_MONEY(3);
    
    private final int code;
    private MoneyType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return code;
    }
    
    public static MoneyType valueOf(int code){
        switch(code){
            case 0 : return NA;
            case 1 : return HARD_MONEY;
            case 2 : return BANK_MONEY;
            case 3 : return E_WALLET_MONEY;
            default: return NA;
        }
    }
    
}
