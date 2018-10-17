
package com.sdigitizers.utils.crypto.codec;

/**
 * @see https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 * @author Shriram Prajapat
 */
public enum SecretKeyFactory implements CryptoAlgorithm {
    
    AES, ARCFOUR, DES, DESede, PBKDF2WithHmacSHA1, ;
    
}
