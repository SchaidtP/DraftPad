package br.com.draftpad.service.auth.response;

import java.io.Serializable;
import java.util.Date;

public class ResponseToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;

    public ResponseToken() {}

    public ResponseToken(String userName, Boolean authenticated, Date created, Date expiration, String accessToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
