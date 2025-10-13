package com.vecino.vecino.dto;

import java.time.LocalDate;

/**
 * DTO para exponer la información de una Persona.
 * Se construye utilizando el patrón Builder para asegurar la inmutabilidad del objeto una vez creado.
 */
public class PersonaDto {

    private final Integer rut;
    private final String vrut;
    private final String nombres;
    private final String paterno;
    private final String materno;
    private final String email;
    private final String estadoCivil;
    private final String genero;
    private final Integer telefono;
    private final String nacionalidad;
    private final LocalDate fechaNac;

    private PersonaDto(Builder builder) {
        this.rut = builder.rut;
        this.vrut = builder.vrut;
        this.nombres = builder.nombres;
        this.paterno = builder.paterno;
        this.materno = builder.materno;
        this.email = builder.email;
        this.estadoCivil = builder.estadoCivil;
        this.genero = builder.genero;
        this.telefono = builder.telefono;
        this.nacionalidad = builder.nacionalidad;
        this.fechaNac = builder.fechaNac;
    }

    // Getters
    public Integer getRut() { return rut; }
    public String getVrut() { return vrut; }
    public String getNombres() { return nombres; }
    public String getPaterno() { return paterno; }
    public String getMaterno() { return materno; }
    public String getEmail() { return email; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getGenero() { return genero; }
    public Integer getTelefono() { return telefono; }
    public String getNacionalidad() { return nacionalidad; }
    public LocalDate getFechaNac() { return fechaNac; }

    public static class Builder {
        private Integer rut;
        private String vrut;
        private String nombres;
        private String paterno;
        private String materno;
        private String email;
        private String estadoCivil;
        private String genero;
        private Integer telefono;
        private String nacionalidad;
        private LocalDate fechaNac;

        public Builder rut(Integer rut) { this.rut = rut; return this; }
        public Builder vrut(String vrut) { this.vrut = vrut; return this; }
        public Builder nombres(String nombres) { this.nombres = nombres; return this; }
        public Builder paterno(String paterno) { this.paterno = paterno; return this; }
        public Builder materno(String materno) { this.materno = materno; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder estadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; return this; }
        public Builder genero(String genero) { this.genero = genero; return this; }
        public Builder telefono(Integer telefono) { this.telefono = telefono; return this; }
        public Builder nacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; return this; }
        public Builder fechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; return this; }

        public PersonaDto build() {
            return new PersonaDto(this);
        }
    }
}