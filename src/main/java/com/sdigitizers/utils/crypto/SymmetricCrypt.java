
package com.sdigitizers.utils.crypto;

import com.sdigitizers.utils.crypto.codec.CipherAlgorithm;
import com.sdigitizers.utils.fileh.FileUtil;
import com.sdigitizers.utils.util.TextUtil;
import java.io.File;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Java : Encryption and Decryption of Data using AES algorithm
 * For encryption we must use a secret key along with an algorithm. 
 * In the following example we use an algorithm called AES 128 and the bytes of the word "TheBestSecretKey" as the secret key. AES algorithm can use a key of 128 bits (16 bytes * 8); so we selected that key.
 * @author Shriram Prajapat
 * @see http://tutorials.jenkov.com/java-cryptography/cipher.html
 */
public class SymmetricCrypt {
    
    private static final Logger LOGGER = LogManager.getLogger(SymmetricCrypt.class);
    
    private CipherAlgorithm algorithm;
    private Key secretKey;
    private Cipher cipher;
    
    private SymmetricCrypt(){
        this.setAlgorithm(CipherAlgorithm.AES);
        byte[] keyValue = new byte[] { 'A', 'b', 'r', 'a', 'K', 'a', 'D', 'a', 'b', 'r', 'a','2', '0', '1', '8', '@'};
        this.secretKey = new SecretKeySpec(keyValue, algorithm.toString());
    }
    
    private final static SymmetricCrypt ENCRYPT = new SymmetricCrypt();
    
    public final static SymmetricCrypt perform(){
        return ENCRYPT;
    }
    
    private Key generateKey(String keyValue){
        return new SecretKeySpec(keyValue.getBytes(), algorithm.toString());
    }
    
    public final SymmetricCrypt setAlgorithm(CipherAlgorithm algorithm){
        this.algorithm = algorithm;
        try {
            cipher = Cipher.getInstance(algorithm.toString());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            LOGGER.error("Algorithm Initialization error : ", ex);
        }
        return this;
    }
    
    public final SymmetricCrypt setSecretKey(String keyValue){
        this.secretKey = generateKey(keyValue);
        return this;
    }
    
    public final String encrypt(String Data) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encVal = cipher.doFinal(Data.getBytes());
        return TextUtil.encodeToBase64(encVal);
    }

    public final String decrypt(String encryptedData) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decordedValue = TextUtil.decodeFromBase64(encryptedData);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
    public final void encrypt(File inputFile, File outputFile){
        fileProcessor(Cipher.ENCRYPT_MODE, inputFile, outputFile);
    }
    public final void decrypt(File inputFile, File outputFile){
        fileProcessor(Cipher.DECRYPT_MODE, inputFile, outputFile);
    }
    
//    private void fileProcessor(int cipherMode, File inputFile, File outputFile){
//        try {
//                cipher.init(cipherMode, secretKey);
//                FileOutputStream outputStream;
//                try (FileInputStream inputStream = new FileInputStream(inputFile)) {
//                    byte[] inputBytes = new byte[(int) inputFile.length()];
//                    inputStream.read(inputBytes);
//                    byte[] outputBytes = cipher.doFinal(inputBytes);
//                    outputStream = new FileOutputStream(outputFile);
//                    outputStream.write(outputBytes);
//                }
//                outputStream.close();
//
//        } catch (InvalidKeyException | BadPaddingException
//                | IllegalBlockSizeException | IOException ex) {
//            LOGGER.error("Cipher error : ", ex);
//        }
//     }
    
    private void fileProcessor(int cipherMode, File inputFile, File outputFile){
        try {
            cipher.init(cipherMode, secretKey);
                byte[] inputByte = FileUtil.toBytes(inputFile);
                byte[] outputByte = cipher.doFinal(inputByte);
                FileUtil.writeToFile(outputFile, outputByte);

        } catch (InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException ex) {
            LOGGER.error("Cipher error : ", ex);
        }
     }
    
}
