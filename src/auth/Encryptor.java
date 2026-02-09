package auth;
//Author: Krish Pillai
import common.Constants;

import javax.crypto.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class Encryptor implements Constants {
    private final byte[] cipherText;
	
	public Encryptor(byte[] clearText, String alg, SecretKey key) 
		throws BadPaddingException, NoSuchAlgorithmException, 
		NoSuchPaddingException, 
		InvalidKeyException, IOException{
		
		// Get cipher object for specified algorithm
        Cipher cipher = Cipher.getInstance(alg);
		
		//Initialize cipher
		cipher.init(Cipher.ENCRYPT_MODE, key);
		try {
			cipherText = cipher.doFinal(clearText);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public byte[] getCipherText() {
		return cipherText;
	}
	
	@Override
	public String toString() {
		return new String(cipherText);
	}
} //EOF
