
package com.sdigitizers.utils.crypto.hash;

import com.sdigitizers.utils.crypto.codec.MessageDigestAlgorithm;
import com.sdigitizers.utils.fileh.FileUtil;
import java.io.*;
import java.security.*;
import org.apache.logging.log4j.LogManager;

/**
 * @see http://tutorials.jenkov.com/java-cryptography/messagedigest.html
 * @author Shriram Prajapat
 */
public class HashUtil {
    
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(HashUtil.class);
    
    //http://www.gravatar.com/avatar/6663995c878359a292aba23d31719712
    public static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));        
        }
        return sb.toString();
    }
    
    
    public static String digest(MessageDigestAlgorithm algorithm, String message) {
        try {
        MessageDigest md =  MessageDigest.getInstance(algorithm.toString());
        return hex (md.digest(message.getBytes("UTF-8"))); // CP1252 -> UTF-8
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return null;
    }
    
    public static String digest(MessageDigestAlgorithm algorithm, File file) {
        try {
        MessageDigest md =  MessageDigest.getInstance(algorithm.toString());
        return hex (md.digest(FileUtil.toBytes(file)));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
        }
        return null;
    }
    
    
    public static String md5Hex(String message){
        return digest(MessageDigestAlgorithm.MD5, message);
    }
    
    public static String sha256Hex(String message){
        return digest(MessageDigestAlgorithm.SHA256, message);
    }
    
    public static String md5Hex(File file){
        return digest(MessageDigestAlgorithm.MD5, file);
    }
    
    public static String sha256Hex(File file){
        return digest(MessageDigestAlgorithm.SHA256, file);
    }
    
}
