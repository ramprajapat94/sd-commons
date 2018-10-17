
package com.sdigitizers.utils.crypto.codec;

/**
 * @see https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 * @author Shriram Prajapat
 */
public enum KeyPairGeneratorAlgorithm implements CryptoAlgorithm {
    
    DiffieHellman, DSA, RSA, EC;
    
}
