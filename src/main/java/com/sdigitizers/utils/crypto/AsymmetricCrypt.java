
package com.sdigitizers.utils.crypto;

/**
 *
 * @author Shriram Prajapat
 */
import com.sdigitizers.utils.fileh.FileUtil;
import com.sdigitizers.utils.util.TextUtil;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AsymmetricCrypt {
    
	private static Cipher cipher;// = Cipher.getInstance("RSA");

        static{
            try {
                cipher = Cipher.getInstance("RSA");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
                Logger.getLogger(AsymmetricCrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
	public static void encryptFile(File input, File output, PrivateKey key) 
		throws IOException, GeneralSecurityException {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		FileUtil.writeToFile(output, cipher.doFinal(FileUtil.toBytes(input)));
	}

	public static void decryptFile(File input, File output, PublicKey key) 
		throws IOException, GeneralSecurityException {
		cipher.init(Cipher.DECRYPT_MODE, key);
		FileUtil.writeToFile(output, cipher.doFinal(FileUtil.toBytes(input)));
	}

	public static String encryptText(String msg, PrivateKey key) 
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, 
			BadPaddingException, InvalidKeyException {
		cipher.init(Cipher.ENCRYPT_MODE, key);
                return TextUtil.encodeToBase64(cipher.doFinal(msg.getBytes("UTF-8")));
		//return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public static String decryptText(String msg, PublicKey key)
			throws InvalidKeyException, UnsupportedEncodingException, 
			IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(TextUtil.decodeFromBase64(msg)), "UTF-8");
	}
}
