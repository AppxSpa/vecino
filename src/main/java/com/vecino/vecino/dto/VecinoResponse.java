package com.vecino.vecino.dto;

/**
 * DTO para la respuesta de información de un Vecino.
 * Contiene los datos personales y de dirección que se exponen al cliente.
 * Se utiliza el patrón Builder para su construcción, asegurando un objeto
 * inmutable una vez creado.
 */
public class VecinoResponse {

    private final Integer rut;
    private final String vrut;
    private final String nombres;
    private final String paterno;
    private final String materno;
    private final String email;
    private final String calle;
    private final Integer nro;
    private final String adicional;
    private final String comuna;
    private final String provincia;
    private final String region;
    private final String genero;
    private final String nacionalidad;
    private final String estadoCivil;
    private final Integer telefono;
    private final String fechaNacimiento;

    private VecinoResponse(Builder builder) {
        this.rut = builder.rut;
        this.vrut = builder.vrut;
        this.nombres = builder.nombres;
        this.paterno = builder.paterno;
        this.materno = builder.materno;
        this.email = builder.email;
        this.calle = builder.calle;
        this.nro = builder.nro;
        this.adicional = builder.adicional;
        this.comuna = builder.comuna;
        this.provincia = builder.provincia;
        this.region = builder.region;
        this.genero = builder.genero;
        this.nacionalidad = builder.nacionalidad;
        this.estadoCivil = builder.estadoCivil;
        this.telefono = builder.telefono;
        this.fechaNacimiento = builder.fechaNacimiento;

    }

    // Getters
    public Integer getRut() {
        return rut;
    }

    public String getVrut() {
        return vrut;
    }

    public String getNombres() {
        return nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public String getEmail() {
        return email;
    }

    public String getCalle() {
        return calle;
    }

    public Integer getNro() {
        return nro;
    }

    public String getAdicional() {
        return adicional;
    }

    public String getComuna() {
        return comuna;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getRegion() {
        return region;
    }

    public String getGenero() {
        return genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public static class Builder {
        private Integer rut;
        private String vrut;
        private String nombres;
        private String paterno;
        private String materno;
        private String email;
        private String calle;
        private Integer nro;
        private String adicional;
        private String comuna;
        private String provincia;
        private String region;
        private String genero;
        private String nacionalidad;
        private String estadoCivil;
        private Integer telefono;
        private String fechaNacimiento;

        public Builder rut(Integer rut) {
            this.rut = rut;
            return this;
        }

        public Builder vrut(String vrut) {
            this.vrut = vrut;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder paterno(String paterno) {
            this.paterno = paterno;
            return this;
        }

        public Builder materno(String materno) {
            this.materno = materno;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder calle(String calle) {
            this.calle = calle;
            return this;
        }

        public Builder nro(Integer nro) {
            this.nro = nro;
            return this;
        }

        public Builder adicional(String adicional) {
            this.adicional = adicional;
            return this;
        }

        public Builder comuna(String comuna) {
            this.comuna = comuna;
            return this;
        }

        public Builder provincia(String provincia) {
            this.provincia = provincia;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public VecinoResponse build() {
            return new VecinoResponse(this);
        }

        public Builder genero(String genero) {
            this.genero = genero;
            return this;
        }

        public Builder nacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
            return this;
        }

        public Builder estadoCivil(String estadoCivil) {
            this.estadoCivil = estadoCivil;
            return this;
        }

        public Builder telefono(Integer telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder fechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }
    }
}