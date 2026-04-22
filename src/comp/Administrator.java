package comp;

import javax.crypto.SecretKey;
import java.io.Serializable;

public class Administrator implements Serializable {
    private String userName;
    private byte[] password;
    private SecretKey secretKey;
    public Administrator(String userName, byte[] password, SecretKey secretKey) {
        this.userName = userName;
        this.password = password;
        this.secretKey = secretKey;
    }
    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
