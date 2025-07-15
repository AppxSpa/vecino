package com.vecino.vecino.dto;

public class MailRequest {

    private Integer rut;
    private String email;

    public MailRequest(Integer rut, String email) {
        this.rut = rut;
        this.email = email;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
