package com.vecino.vecino.dto;

public class UsuarioRequest {

    private String username;
    private String password;

    public UsuarioRequest() {

    }

    public UsuarioRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public String getPassword() {
        return this.password;
    }

    public void setPaswword(String password) {
        this.password = password;
    }

}
