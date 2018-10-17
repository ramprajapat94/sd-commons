
package com.sdigitizers.utils.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Shriram Prajapat
 */
public class TextUtil {
 
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TextUtil.class);
    /**
     * If the String is null it returns empty string else the original String
     * @param s String to be secured
     * @return "" if null else the original string
     */
    public static String maskNull(String s){
        return (s==null)?"":s;
    }
    
    /**
     * Removes single quote from the string
     * @param s String to be formatted
     * @return Formatted String
     */
    public static String removeSingleQuote(String s){
      if(s!=null){
        if(s.contains("'")) {
               return s.replace(s.charAt(s.indexOf("'")),' ');
        } return s; 
      } return s;
     }
 

    /**
     * To make the first letter capital in the specified string
     * @param s String to be formatted
     * @return Formatted String
     */
    public static String firstCapital(String s)  {
      if(s.length()>1){
         return Character.toString(s.charAt(0)).toUpperCase()+s.substring(1);
      }return null;
    }


    /**
     * To make the first letter capital of each word in the string
     * @param s String to be formatted
     * @return Formatted String
     */
    public static String capitalizeString(String s) {
        int length = s.length();
        StringBuffer newStr = new StringBuffer(0);
        int i = 0;
        int k = 0;
       while( i < length){
            if (Character.isLetter(s.charAt(i))){
                if ( k == 0){
                   newStr = newStr.append(Character.toUpperCase(s.charAt(i)));
                   k = 2;
                }//this else loop is to avoid repeatation of the first letter in output string 
                else {
                   newStr = newStr.append(s.charAt(i));
                }
            } // for the letters which are not first letter, simply append to the output string. 
            else {
                newStr = newStr.append(s.charAt(i));
                k=0;
            }
            i+=1;           
        }
        return ""+newStr;
     }    


    
    public static String streamToText(InputStream inputStream){
        if(null == inputStream)return "";
        StringBuilder output = new StringBuilder();
        String line;			
        try(BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = reader.readLine())!= null) {
                output.append(line).append("\n");
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        return output.toString();
    }
    
    public static byte[] toBytes(String s, String charsetName) throws UnsupportedEncodingException{
        return s.getBytes(charsetName);
    }
    public static byte[] toBytes(String s) throws UnsupportedEncodingException{
        return toBytes(s, "UTF-8");
    }
    
    public static String toString(byte[] b, String charsetName) throws UnsupportedEncodingException{
        return new String(b, charsetName);
    }
    public static String toString(byte[] b) throws UnsupportedEncodingException{
        return new String(b, "UTF-8");
    }
    
    public static String encodeToBase64(byte[] b){
        return new BASE64Encoder().encode(b);
    }
    public static byte[] decodeFromBase64(String s){
        try {
            return new BASE64Decoder().decodeBuffer(s);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    
}
