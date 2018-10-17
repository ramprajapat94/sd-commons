
package com.sdigitizers.utils.crypto.codec;

/**
 * @see https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 * @author Shriram Prajapat
 */
public enum MacAlgorithm implements CryptoAlgorithm{
    
    HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512, ;
    
}
