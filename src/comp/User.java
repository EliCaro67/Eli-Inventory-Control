package comp;

import auth.Credentials;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    // User DTO -- Data Transfer Object
    private String firstName, lastName;
    private Credentials credentials;
    private Role primaryRole;
    /**
     * Inner class for login and password
     * @param login
     * @param password
     */
    public User(String firstName,
                String lastName,
                String login,
                byte[] password) {
        this.credentials = new Credentials(login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.primaryRole = Role.values()[0];
    }

    public String getLogin() {
        return credentials.login();
    }

    public byte[] getPassword() {
        return credentials.password();
    }

    public void setPassword(byte[] password) {
        this.credentials = new Credentials(credentials.login(), password);
    }

    public void setLogin(String login) {
        this.credentials = new Credentials(login, credentials.password());
    }

    public void setCredentials(String login, byte[] password) {
        this.credentials = new Credentials(login, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(Role primaryRole) {
        this.primaryRole = primaryRole;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(credentials, user.credentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, credentials);
    }

    @Override
    public String toString() {
        return "User{" +
                "credentials=" + credentials +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + primaryRole + '\'' +
                '}';
    }

}
