package com.vecino.vecino.config;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private String usuarioUrl;
    private String direccionUrl;

    

    public String getUsuarioUrl() {
        return usuarioUrl;
    }

    public void setUsuarioUrl(String usuarioUrl) {
        this.usuarioUrl = usuarioUrl;
    }

    public String getDireccionUrl() {
        return direccionUrl;
    }

    public void setDireccionUrl(String apiDireccion) {
        this.direccionUrl = apiDireccion;
    }

}
