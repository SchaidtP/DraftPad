package br.com.draftpad.service.auth.response;

import br.com.draftpad.domain.user.UserRole;

import java.io.Serializable;
import java.util.Date;

public class ResponseToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String role;

    private String token;

    public ResponseToken() {}

    public ResponseToken(String userName, String role, String token) {
        this.userName = userName;
        this.role = role;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
