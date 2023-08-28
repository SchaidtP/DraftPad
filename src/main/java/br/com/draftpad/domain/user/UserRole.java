package br.com.draftpad.domain.user;

public enum UserRole {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
