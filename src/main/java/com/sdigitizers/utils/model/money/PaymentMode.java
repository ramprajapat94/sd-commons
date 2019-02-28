
package com.sdigitizers.utils.model.money;

/**
 *
 * @author Shriram Prajapat
 */
public enum PaymentMode {
    CASH(0), CHEQUE(1), BANK_TRANSFER(2), 
    IMPS(3), NEFT(4), PAYTM(5), E_WALLET(6), 
    UPI(7), MONEY_ORDER(8), DIRECT_CREDIT(9), 
    CARD_PAYMENT(10),  CREDIT_NOTE(11), DEBIT_NOTE(12),
    CUSTOMER_WALLET(13), DIRECT_MC(14), 
    AMEX(15), EFT_POS(16), WRITE_OFF(17), CONTRA(18)
    ;
    
    private final int code;
    private PaymentMode(int code){
       this.code = code;
    }
   
    public int getCode(){
        return code;
    }    
    
    public static PaymentMode valueOf(int code){
        switch(code){
            case 0 : return CASH;
            case 1 : return CHEQUE;
            case 2 : return BANK_TRANSFER;
            case 3 : return IMPS;
            case 4 : return NEFT;
            case 5 : return PAYTM;
            case 6 : return E_WALLET;
            case 7 : return UPI;
            case 8 : return MONEY_ORDER;
            case 9 : return DIRECT_CREDIT;
            case 10 : return CARD_PAYMENT;
            case 11 : return CREDIT_NOTE;
            case 12 : return DEBIT_NOTE;
            case 13 : return CUSTOMER_WALLET;
            case 14 : return DIRECT_MC;
            case 15 : return AMEX;
            case 16 : return EFT_POS;
            case 17 : return WRITE_OFF;
            case 18 : return CONTRA;
            default : return CASH;
        }
    }

}
