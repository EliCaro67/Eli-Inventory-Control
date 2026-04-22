/**
 *
 */
package auth;

import common.Constants;
import common.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 * @author pillai
 *
 */
public final class AccountsManager implements Constants {

    /**
     * Authenticator
     */
    private SecretKey key;
    private final String algorithms = "DES/ECB/PKCS5Padding";
    public static String algorithmInUse = "DES";



    public AccountsManager() {
        try {
            key = Utils.loadFromFile(KEY_DB);
            logger.info("KEY loaded!");
        } catch (IOException e1) {
            try {
                key = new SecretKeyGenerator(algorithmInUse).getSecretKey();
                Utils.commitToFile(key, KEY_DB);
                logger.info("New KEY generated!");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds login and password to the map.
     * @param password
     * @return
     */
    public byte[] encryptPassword(byte[] password) {
        if (password == null) {
            logger.log(Level.SEVERE, "No password supplied!");
            throw new IllegalArgumentException("No password supplied!");
        }

        try {
            Encryptor encrypt = new Encryptor(password, algorithms, key);
            password = encrypt.getCipherText();
        } catch (InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException
                 | IOException e) {
            logger.log(Level.SEVERE, "Error encrypting password!");
            throw new RuntimeException(e.getMessage());
        }
       return password;
    }

    public byte[] decryptPassword(byte[] password) {
        try {
            Decryptor decrypt = new Decryptor(password, algorithms, key);
            return decrypt.getClearText();
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
