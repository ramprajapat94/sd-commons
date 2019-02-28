
package com.sdigitizers.utils.model.money;

/**
 *
 * @author Shriram Prajapat
 */
public enum TransactionType {
    CREDIT(0), DEBIT(1);
    
    private final int code;
    private TransactionType(int code){
       this.code = code;
    }
    public int getCode(){
        return code;
    }
    
    public static TransactionType valueOf(int code){
        switch(code){
            case 0 : return CREDIT;
            case 1 : return DEBIT;
            default : return CREDIT;
        }
    }
}
