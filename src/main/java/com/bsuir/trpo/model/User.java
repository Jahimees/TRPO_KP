package com.bsuir.trpo.model;

public class User {
    private String login;
    private String saltedHashPassword;
    private String salt;
    private boolean role; // 0 - user, 1 - admin;
    private boolean access;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSaltedHashPassword() {
        return saltedHashPassword;
    }

    public void setSaltedHashPassword(String saltedHashPassword) {
        this.saltedHashPassword = saltedHashPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
