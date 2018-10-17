
package com.sdigitizers.utils.util;

import java.text.DecimalFormat;
import java.util.Currency;

/**
 *
 * @author Shriram Prajapat
 */
public class MoneyUtil {
    
    public final static Currency CURRENCY = Currency.getInstance("INR");
    public final static String INR_SYMBOL_CODE = "\u20B9";
    
    /** INR ##,##,##,##0.00 */
    public final static DecimalFormat MONEY = Format.MONEY;
    
    public static String getAmountInWords(long amount){
        if(amount>=0){
            String amn = String.valueOf(amount);
            switch(amn.length()){
                case 1 : return getNumberName((int)amount);
                case 2 : return getNumberName(Integer.parseInt(amn.charAt(0)+"")*10)+" "+getAmountInWords(Integer.parseInt(amn.charAt(1)+""));
                case 3 : return getNumberName(Integer.parseInt(amn.charAt(0)+""))+" Hundred "+getAmountInWords(Integer.parseInt(amn.substring(1)));
                case 4 : amn = "0"+amn ;
                case 5 : return getAmountInWords(Integer.parseInt(amn.substring(0, 2)))+" Thousand "+getAmountInWords(Integer.parseInt(amn.substring(2)));
                case 6 : amn = "0"+amn ;
                case 7 : return getAmountInWords(Integer.parseInt(amn.substring(0, 2)))+" Lakh "+getAmountInWords(Integer.parseInt(amn.substring(2)));
                case 8 : amn = "0"+amn ; 
                case 9 : return getAmountInWords(Integer.parseInt(amn.substring(0, 2)))+" Crore "+getAmountInWords(Integer.parseInt(amn.substring(2)));
                case 10 : amn = "0"+amn ;
                case 11 : return getAmountInWords(Integer.parseInt(amn.substring(0, 2)))+" Arab "+getAmountInWords(Long.parseLong(amn.substring(2)));
                default : return "Unparsable Amount";
            }
        }else{
            return "Invalid Amount!";
        }
    }
    
    private static String getNumberName(int n){
        switch(n){
          case 0 : return "";
          case 1 : return "One";
          case 2 : return "Two";
          case 3 : return "Three";
          case 4 : return "Four";
          case 5 : return "Five";
          case 6 : return "Six";
          case 7 : return "Seven";
          case 8 : return "Eight";
          case 9 : return "Nine";
          case 10 : return "Ten";
          case 11 : return "Eleven";
          case 12 : return "Tweleve";
          case 13 : return "Thirteen";
          case 14 : return "Fourteen";
          case 15 : return "Fifteen";
          case 16 : return "Sixteen";
          case 17 : return "Seventeen";
          case 18 : return "Eighteen";
          case 19 : return "Nineteen";
          case 20 : return "Twenty";
          case 30 : return "Thirty";
          case 40 : return "Fourty";
          case 50 : return "Fifty";
          case 60 : return "Sixty";
          case 70 : return "Seventy";
          case 80 : return "Eighty";
          case 90 : return "Ninety";
          default : return "Invalid Number";
        }
    }
//    static{
//        System.out.println(Class.class+" "+CURRENCY.getCurrencyCode()+ " "+CURRENCY.getDisplayName()+" "+CURRENCY.getSymbol()+"  "+CURRENCY.getDefaultFractionDigits()+" "+CURRENCY.getNumericCode());
//    }
//     public final static CurrencyUnit cu = Monetary.getCurrency("INR");
//    public final static FastMoney fm = FastMoney.of(200, cu);   

    /**
     * Round off the specified number (upto two decimal places)
     * @param d Number
     * @return number with two decimal places
     */
    public static double roundOffNumber(Double d) {
        return (double) Math.round(d * 100) / 100;
    }
}
