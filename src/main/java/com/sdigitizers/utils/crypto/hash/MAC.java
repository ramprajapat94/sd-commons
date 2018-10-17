
package com.sdigitizers.utils.crypto.hash;

import com.sdigitizers.utils.crypto.codec.MacAlgorithm;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;

/**
 * @see http://tutorials.jenkov.com/java-cryptography/mac.html
 * @author Shriram Prajapat
 * MAC - Message Authentication Code
 */
public class MAC {
    
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MAC.class);
    
    private Mac mac;
    private String keyValue = "TheBestKey";
    private Key secretKey;
    private MacAlgorithm algorithm;
    
    private MAC(){
        setAlgorithm(MacAlgorithm.HmacSHA256);
        setKey(keyValue);
    }
    
    private static final MAC macObject = new MAC();
    
    public static final MAC perform(){
        return macObject;
    }
    
    public final MAC setAlgorithm(MacAlgorithm algorithm){
        try {
            this.algorithm = algorithm;
            mac = Mac.getInstance(algorithm.toString());
            setKey(keyValue);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
        }
        return this;
    }
    
    public final MacAlgorithm getAlgorithm(){
        return algorithm;
    }
    
    public final MAC setKey(String keyValue){
        try {
            this.keyValue = keyValue;
            secretKey = new SecretKeySpec(keyValue.getBytes(), "RawBytes");
            mac.init(secretKey);
        } catch (InvalidKeyException ex) {
            LOGGER.error(ex);
        }
        return this;
    }
    
    public String getKey(){
        return keyValue;
    }
    
    public final String generateMAC(String message){
        try {
            byte[] data  = message.getBytes("UTF-8");
            byte[] macBytes = mac.doFinal(data);
            return HashUtil.hex(macBytes);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
}
