
package com.sdigitizers.utils.security;

import com.sdigitizers.utils.crypto.codec.KeyPairGeneratorAlgorithm;
import com.sdigitizers.utils.crypto.codec.SignatureAlgorithm;
import com.sdigitizers.utils.util.TextUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import org.apache.logging.log4j.LogManager;

/**
 * @see http://tutorials.jenkov.com/java-cryptography/signature.html
 * @author Shriram Prajapat
 */
public class SignatureUtil {
    
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(SignatureUtil.class);
    
    private Signature signature;
    private SignatureAlgorithm algorithm;    
    
    private SignatureUtil(){
        setAlgorithm(SignatureAlgorithm.SHA256withDSA); 
    }
    
    private final static SignatureUtil signatureUtil = new SignatureUtil();
    public static final SignatureUtil perform(){
        return signatureUtil;
    }
    
    /**
     * @param algorithm Algorithm to be used for generating digital signature 
     * [Key algorithm and the signature algorithm must be compatible with each other,
     * since the key is used to generate the signature. Hence, incompatibility may lead to exception]
     * @return this instance
     */
    public final SignatureUtil setAlgorithm(SignatureAlgorithm algorithm){
        this.algorithm  = algorithm;
        try {
            this.signature = Signature.getInstance(algorithm.toString());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
        }
        return this;
    }
    /**
     * @return The algorithm being used for signature instance
     */
    public final SignatureAlgorithm getAlgorithm(){
        return algorithm;
    }
    
    /**
     * @param data Data to sign
     * @param privateKey Private key [Key used to sign]
     * @return Signature value
     */
    public final String sign(String data, PrivateKey privateKey){
        final SecureRandom secureRandom = new SecureRandom();
        try {
            signature.initSign(privateKey, secureRandom);
            byte[] dataBytes = data.getBytes("UTF-8");
            signature.update(dataBytes);
            byte[] digitalSignature = signature.sign();
            return TextUtil.encodeToBase64(digitalSignature);
        } catch (InvalidKeyException | SignatureException | UnsupportedEncodingException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    /**
     * @param data Data to verify
     * @param signatureValue The signature value
     * @param publicKey Public key
     * @return whether verified or not
     */
    public final boolean verify(String data, String signatureValue, PublicKey publicKey){
        try {
            byte[] decordedValue = TextUtil.decodeFromBase64(signatureValue);
            byte[] dataBytes = data.getBytes("UTF-8");
            signature.initVerify(publicKey);
            signature.update(dataBytes);
            return signature.verify(decordedValue);
        } catch (InvalidKeyException | SignatureException | IOException ex) {
            LOGGER.error(ex);
        }
        return false;
    }
    
    /**
     * @param data Data to verify
     * @param signatureValue The signature value
     * @param certificate Certificate
     * @return whether verified or not
     */
    public final boolean verify(String data, String signatureValue, Certificate certificate){
        try {
            byte[] decordedValue = TextUtil.decodeFromBase64(signatureValue);
            byte[] dataBytes = data.getBytes("UTF-8");
            signature.initVerify(certificate);
            signature.update(dataBytes);
            return signature.verify(decordedValue);
        } catch (InvalidKeyException | SignatureException | IOException ex) {
            LOGGER.error(ex);
        }
        return false;
    }
    
    public static void test() {
        KeyPair kp = KeyUtils.generateKeyPair(KeyPairGeneratorAlgorithm.DSA);
        String s = SignatureUtil.perform().sign("Hello", kp.getPrivate());
        boolean b = SignatureUtil.perform().verify("Hello", s, kp.getPublic());
        System.out.println("Test result : "+b);
    }
    
    
}
