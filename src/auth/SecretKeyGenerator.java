package auth;
//Author: Krish Pillai
import javax.crypto.*;
import java.security.NoSuchAlgorithmException;

public class SecretKeyGenerator{
	private SecretKey key;
	KeyGenerator keygen;
  
	public SecretKeyGenerator(String alg) throws NoSuchAlgorithmException {
		 this.keygen = KeyGenerator.getInstance(alg);
		 this.key = keygen.generateKey();
	}
	
	public SecretKey getSecretKey(){
		return this.key;
	}
}