
package com.sdigitizers.utils.security;

import com.sdigitizers.utils.crypto.codec.CryptoAlgorithm;
import com.sdigitizers.utils.crypto.codec.KeyGeneratorAlgorithm;
import com.sdigitizers.utils.crypto.codec.KeyPairGeneratorAlgorithm;
import com.sdigitizers.utils.crypto.codec.KeyStoreType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.logging.log4j.LogManager;
import sun.security.util.KeyUtil;

/**
 *
 * @author Shriram Prajapat
 */
public class KeyUtils {
    
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(KeyUtils.class);
    
    /**
     * Default Algorithm = AES,<br> Default Key Size = 256 bits
     * @return SecretKey
     */
    public static SecretKey generateSecretKey(){
        return generateSecretKey(KeyGeneratorAlgorithm.AES);
    }
    /**
     * Default Key Size = 256 bits
     * @param algorithm
     * @return Secret Key
     */
    public static SecretKey generateSecretKey(KeyGeneratorAlgorithm algorithm){
        return generateSecretKey(algorithm, 256);
    }
    public static SecretKey generateSecretKey(KeyGeneratorAlgorithm algorithm, int keyBitSize){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.toString());
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(keyBitSize, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    /**
     * Default Algorithm = RSA, <br> Default Key Size = 2048 bits
     * @return KeyPair
     */
    public static KeyPair generateKeyPair(){
        return generateKeyPair(KeyPairGeneratorAlgorithm.RSA);
    }
    public static KeyPair generateKeyPair(KeyPairGeneratorAlgorithm algorithm){
        return generateKeyPair(algorithm, 1024);
    }
    public static KeyPair generateKeyPair(KeyPairGeneratorAlgorithm algorithm, int keyBitSize){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.toString());
            keyPairGenerator.initialize(keyBitSize);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/PKCS8EncodedKeySpec.html
	public PrivateKey loadPrivateKey(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	// https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
	public PublicKey loadPublicKey(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
    
    public static void writeToFile(String path, Key key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(key.getEncoded());
                fos.flush();
            }
    }
    
    public static KeyStore generateKeyStore(String keyStorePassword){
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, keyStorePassword.toCharArray());
            return keyStore;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    public static KeyStore generateKeyStore(KeyStoreType algorithm, String keyStorePassword){
        try {
            KeyStore keyStore = KeyStore.getInstance(algorithm.toString());
            keyStore.load(null, keyStorePassword.toCharArray());
            return keyStore;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    /**
     * 
     * @param fileName (eg. keystore.ks)
     * @param keyStorePassword
     * @return 
     */
    
    public static KeyStore loadKeyStore(String fileName, String keyStorePassword){
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try(InputStream keyStoreData = new FileInputStream(fileName)){
                keyStore.load(keyStoreData, keyStorePassword.toCharArray());
                return keyStore;
            }
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    public static KeyStore loadKeyStore(String filePath, KeyStoreType algorithm, String keyStorePassword){
        try {
            KeyStore keyStore = KeyStore.getInstance(algorithm.toString());
            try(InputStream keyStoreData = new FileInputStream(filePath)){
                keyStore.load(keyStoreData, keyStorePassword.toCharArray());
                return keyStore;
            }
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    public static void saveKeyStore(KeyStore keyStore, String filePath, String keyStorePassword){
        try {
            try (FileOutputStream keyStoreOutputStream = new FileOutputStream(filePath)) {
                keyStore.store(keyStoreOutputStream, keyStorePassword.toCharArray());
            }
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            LOGGER.error(ex);
        }
    }
    
    public static KeyStore.Entry getKeyStoreEntry(KeyStore keyStore, String keyAlias, String keyPassword){
        try {
            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword.toCharArray());
            return keyStore.getEntry(keyAlias, entryPassword);
        } catch (NoSuchAlgorithmException | UnrecoverableEntryException | KeyStoreException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    public static Key getKey(KeyStore keyStore, String keyAlias, String keyPassword){
        try {
            return keyStore.getKey(keyAlias, keyPassword.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    public static void setKeyStoreEntry(KeyStore keyStore, String keyAlias, String entryPassword, SecretKey key){
        try {
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(key);
            KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(entryPassword.toCharArray());
            keyStore.setEntry(keyAlias, secretKeyEntry, password);
        } catch (KeyStoreException ex) {
            LOGGER.error(ex);
        }
    }
    public static void setKeyStoreEntry(KeyStore keyStore, String keyAlias, String entryPassword, KeyStore.PrivateKeyEntry key){
        try {
            KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(entryPassword.toCharArray());
            keyStore.setEntry(keyAlias, key, password);
        } catch (KeyStoreException ex) {
            LOGGER.error(ex);
        }
    }
    public static void setKeyStoreEntry(KeyStore keyStore, String keyAlias, String entryPassword, Certificate cert){
        try {
            KeyStore.TrustedCertificateEntry certEntry = new KeyStore.TrustedCertificateEntry(cert);
            KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(entryPassword.toCharArray());
            keyStore.setEntry(keyAlias, certEntry, password);
        } catch (KeyStoreException ex) {
            LOGGER.error(ex);
        }
    }
    
    public static int getKeySize(CryptoAlgorithm ap){
        try {
            return KeyUtil.getKeySize(AlgorithmParameters.getInstance(ap.toString()));
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex);
        }
        return 0;
    }
    
    public static int getKeySize(Key key){
        return KeyUtil.getKeySize(key);
    }
}
