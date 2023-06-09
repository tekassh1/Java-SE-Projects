package commands;

import java.io.Serializable;

public abstract class AuthorizationCommand implements Serializable {
    private String username;
    private String password;

    private String token;

    private boolean result = false;

    public AuthorizationCommand(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}