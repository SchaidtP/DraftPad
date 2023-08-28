package br.com.draftpad.service.user.response;

import java.util.UUID;

public class ResponseGetUsers {
    private UUID id;
    private String userName;
    private String role;

    public ResponseGetUsers(UUID id, String userName, String role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
