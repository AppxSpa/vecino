package com.vecino.vecino.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

/**
 * Entidad base que representa una Persona.
 * Contiene información personal fundamental y es la superclase de otras entidades como Vecino.
 * Se ha implementado el patrón Builder para facilitar la creación de instancias de una manera legible y escalable.
 */
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
    @JoinColumn(name = "estado_civil_id", referencedColumnName = "id")
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "nacionalidad_id", referencedColumnName = "id")
    private Nacionalidad nacionalidad;

    protected Persona() {}

    protected Persona(Builder<?> builder) {
        this.rut = builder.rut;
        this.vrut = builder.vrut;
        this.nombres = builder.nombres;
        this.paterno = builder.paterno;
        this.materno = builder.materno;
        this.fechaNac = builder.fechaNac;
        this.fono = builder.fono;
        this.email = builder.email;
        this.dirId = builder.dirId;
        this.estadoCivil = builder.estadoCivil;
        this.genero = builder.genero;
        this.nacionalidad = builder.nacionalidad;
    }

    // Getters
    public Long getId() { return id; }
    public Integer getRut() { return rut; }
    public String getVrut() { return vrut; }
    public String getNombres() { return nombres; }
    public String getPaterno() { return paterno; }
    public String getMaterno() { return materno; }
    public LocalDate getFechaNac() { return fechaNac; }
    public Integer getFono() { return fono; }
    public Long getDirId() { return dirId; }
    public String getEmail() { return email; }
    public EstadoCivil getEstadoCivil() { return estadoCivil; }
    public Genero getGenero() { return genero; }
    public Nacionalidad getNacionalidad() { return nacionalidad; }

    // Setters para campos que deben ser mutables después de la creación
    public void setDirId(Long dirId) { this.dirId = dirId; }
    public void setEmail(String email) { this.email = email; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }
    public void setGenero(Genero genero) { this.genero = genero; }
    public void setNacionalidad(Nacionalidad nacionalidad) { this.nacionalidad = nacionalidad; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setPaterno(String paterno) { this.paterno = paterno; }
    public void setMaterno(String materno) { this.materno = materno; }
    public void setFechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; }
    public void setFono(Integer fono) { this.fono = fono; }

    /**
     * Builder abstracto para la entidad Persona. Permite la extensión por subclases.
     * @param <T> El tipo de builder concreto que lo extiende.
     */
    public abstract static class Builder<T extends Builder<T>> {
        private Integer rut;
        private String vrut;
        private String nombres;
        private String paterno;
        private String materno;
        private LocalDate fechaNac;
        private Integer fono;
        private String email;
        private Long dirId;
        private EstadoCivil estadoCivil;
        private Genero genero;
        private Nacionalidad nacionalidad;

        public T rut(Integer rut) { this.rut = rut; return self(); }
        public T vrut(String vrut) { this.vrut = vrut; return self(); }
        public T nombres(String nombres) { this.nombres = nombres; return self(); }
        public T paterno(String paterno) { this.paterno = paterno; return self(); }
        public T materno(String materno) { this.materno = materno; return self(); }
        public T fechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; return self(); }
        public T fono(Integer fono) { this.fono = fono; return self(); }
        public T email(String email) { this.email = email; return self(); }
        public T dirId(Long dirId) { this.dirId = dirId; return self(); }
        public T estadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; return self(); }
        public T genero(Genero genero) { this.genero = genero; return self(); }
        public T nacionalidad(Nacionalidad nacionalidad) { this.nacionalidad = nacionalidad; return self(); }

        protected abstract T self();

        public Persona build() {
            return new Persona(this);
        }
    }

    /**
     * Implementación concreta del Builder para la clase Persona.
     * Permite crear instancias de Persona directamente.
     */
    public static class PersonaBuilder extends Builder<PersonaBuilder> {
        @Override
        protected PersonaBuilder self() {
            return this;
        }
    }
}
