
package com.sdigitizers.utils.crypto.codec;

/**
 * @see https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 * @author Shriram Prajapat
 */
public enum CipherAlgorithm implements CryptoAlgorithm{
    
    AES, AESWrap, ARCFOUR, Blowfish, CCM, DES, DESede, DESedeWrap, ECIES, GCM, RC2, RC4, RC5, RSA;
    
}
