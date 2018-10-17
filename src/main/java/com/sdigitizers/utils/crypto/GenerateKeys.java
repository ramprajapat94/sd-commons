
package com.sdigitizers.utils.crypto;

import com.sdigitizers.utils.security.KeyUtils;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Shriram Prajapat
 */


public class GenerateKeys {

	private final KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
                createKeys();
	}

	private void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

        public void writeToFile(String path, Key k){
            try {
                KeyUtils.writeToFile(path, k);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
//	
//
//	public static void main(String[] args) {
//		GenerateKeys gk;
//		try {
//			gk = new GenerateKeys(1024);
//			gk.createKeys();
//			gk.writeToFile("KeyPair/publicKey", gk.getPublicKey());
//			gk.writeToFile("KeyPair/privateKey", gk.getPrivateKey());
//		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//			System.err.println(e.getMessage());
//		}
//
//	}

}
