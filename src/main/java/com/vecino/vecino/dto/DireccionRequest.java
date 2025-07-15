package com.vecino.vecino.dto;

public class DireccionRequest {

    private String nombreCalle;
    private Integer numeroCalle;
    private String nombreComuna;
    private Double longitud;
    private Double latitud;
    private String adicional;

    public DireccionRequest() {
    }

    public DireccionRequest(String nombreCalle, Integer numeroCalle, String nombreComuna, Double longitud,
            Double latitud, String adicional) {
        this.nombreCalle = nombreCalle;
        this.numeroCalle = numeroCalle;
        this.nombreComuna = nombreComuna;
        this.longitud = longitud;
        this.latitud = latitud;
        this.adicional = adicional;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public Integer getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(Integer numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

}
