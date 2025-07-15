package com.vecino.vecino.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer rut;

    private String vrut;
    private String nombres;
    private String paterno;
    private String materno;
    private LocalDate fechaNac;
    private Integer fono;
    private Long dirId;

    
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "estado_civil_id", referencedColumnName = "id", nullable = true)
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "id", nullable = true)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "nacionalidad_id", referencedColumnName = "id", nullable = true)
    private Nacionalidad nacionalidad;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getVrut() {
        return vrut;
    }

    public void setVrut(String vrut) {
        this.vrut = vrut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Integer getFono() {
        return fono;
    }

    public void setFono(Integer fono) {
        this.fono = fono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public String getNombreEstadoCivil() {
        return estadoCivil != null ? estadoCivil.getNombreEstado() : null;
    }

    public String getNombreGenero() {
        return genero != null ? genero.getNombreGenero() : null;
    }

    public String getNombreNacionalidad() {
        return nacionalidad != null ? nacionalidad.getNombreNacionalidad() : null;
    }
}
