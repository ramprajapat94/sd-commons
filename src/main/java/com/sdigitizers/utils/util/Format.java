
package com.sdigitizers.utils.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Shriram Prajapat
 */
public class Format {
    /** 00.0 % */
    public final static DecimalFormat PERCENT = new DecimalFormat("00.00 %");
    //public final static DecimalFormat BILL_NUMBER = new DecimalFormat("000000");
    /** INR ##,##,##,##0.00 */
    public final static DecimalFormat MONEY = new DecimalFormat(MoneyUtil.INR_SYMBOL_CODE+" ##,##,##,##0.00"); //df.setMaximumFractionDigits(2);
    public final static DecimalFormat BALANCE_MONEY = new DecimalFormat(MoneyUtil.INR_SYMBOL_CODE+" ##,##,##,##0.00");
    /** ##,##,##,##0.00 */
    public final static DecimalFormat VALUE = new DecimalFormat("##,##,##,##0.00");
    /** Round of the value */
    public final static DecimalFormat ROUND_OFF = new DecimalFormat("0.00");
    /**23-05-2018 - 04:05:26 PM */
    public final static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd-MM-yyyy - hh:mm:ss a");
    /**04:05:26 PM */
    public final static DateTimeFormatter TIME = DateTimeFormatter.ofPattern("hh:mm:ss a");
    /**23 May 1998*/
    public final static DateTimeFormatter DATE1 = DateTimeFormatter.ofPattern("dd MMM yyyy");
    /**May 23, 1998*/
    public final static DateTimeFormatter DATE2 = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    /**23-05-1998*/
    public final static DateTimeFormatter DATE3 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    /**23/05/1998*/
    public final static DateTimeFormatter DATE4 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
     //Currency.getInstance(Locale.getDefault())
   
    public static int ROUND_OFF_SCALE = 2;
    public static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    
   static{
       PERCENT.setMultiplier(1);
       
       MONEY.setCurrency(MoneyUtil.CURRENCY);
       
       BALANCE_MONEY.setCurrency(MoneyUtil.CURRENCY);
       BALANCE_MONEY.setNegativePrefix("");
       BALANCE_MONEY.setNegativeSuffix(" Dr");
       BALANCE_MONEY.setRoundingMode(RoundingMode.HALF_EVEN);
       BALANCE_MONEY.setPositiveSuffix(" Cr");
       //ROUND_OFF.setMaximumFractionDigits(2);
       //ROUND_OFF.setMinimumFractionDigits(2);
   }
   
   public static String phone(String countryCode, long phone){
       String no = String.valueOf(phone);
       return countryCode+"-"+no.substring(0, 4)+"-"+no.substring(4,7)+"-"+no.substring(7);
   }
   
}
