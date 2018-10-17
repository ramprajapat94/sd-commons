
package com.sdigitizers.utils.crypto.codec;

/**
 * @see https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 * @author Shriram Prajapat
 */
public enum SignatureAlgorithm implements CryptoAlgorithm {
    
    NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA, SHA256withRSA, SHA256withDSA,
    SHA384withRSA, SHA512withRSA, NONEwithDSA, SHA1withDSA,
    NONEwithECDSA, SHA1withECDSA, SHA256withECDSA, 
    SHA384withECDSA, SHA512withECDSA;
    
}
