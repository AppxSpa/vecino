package com.vecino.vecino.dto;

public class DireccionResponse {

    private String nombreCalle;
    private Integer nroCalle;
    private String adicional;
    private String comuna;
    private String provincia;
    private String region;

    public DireccionResponse() {
    }

    public DireccionResponse(String nombreCalle, Integer nroCalle, String adicional, String comuna, String provincia,
            String region) {
        this.nombreCalle = nombreCalle;
        this.nroCalle = nroCalle;
        this.adicional = adicional;
        this.comuna = comuna;
        this.provincia = provincia;
        this.region = region;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public Integer getNroCalle() {
        return nroCalle;
    }

    public void setNroCalle(Integer nroCalle) {
        this.nroCalle = nroCalle;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
