package auth;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public record Credentials(String login, byte[] password) implements Serializable {
    @Override
    public String toString() {
        return "Credentials[login="
                + login
                + ", password="
                + Arrays.toString(password);
    }
}
