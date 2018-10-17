
package com.sdigitizers.utils.crypto.codec;

/**
 * @see http://tutorials.jenkov.com/java-cryptography/messagedigest.html
 * @author Shriram Prajapat
 */
public enum MessageDigestAlgorithm implements CryptoAlgorithm {
    
    MD2("MD2"), MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");
    
    private final String name;
    private MessageDigestAlgorithm(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
