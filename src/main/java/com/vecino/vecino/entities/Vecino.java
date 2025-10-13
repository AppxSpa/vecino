package com.vecino.vecino.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;

/**
 * Entidad que representa a un Vecino.
 * Hereda de Persona y añade campos específicos como el estado y la fecha de registro.
 * Utiliza un Builder anidado que extiende el Builder de Persona para una construcción de objetos fluida y consistente.
 */
@Entity
public class Vecino extends Persona {

    private LocalDate fechaRegistro;
    private Boolean estado;

    /**
     * Constructor protegido para ser usado exclusivamente por el Builder.
     */
    protected Vecino() {
        super();
    }

    /**
     * Constructor que inicializa la entidad a partir de su Builder.
     * @param builder El builder con los datos del vecino.
     */
    private Vecino(Builder builder) {
        super(builder);
        this.fechaRegistro = builder.fechaRegistro;
        this.estado = builder.estado;
    }

    // Getters
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Boolean getEstado() {
        return estado;
    }

    // Setters para campos mutables
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Builder para la entidad Vecino.
     * Extiende el Builder de Persona para incluir todos los campos de la jerarquía.
     */
    public static final class Builder extends Persona.Builder<Builder> {
        private LocalDate fechaRegistro;
        private Boolean estado;

        public Builder fechaRegistro(LocalDate fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
            return this;
        }

        public Builder estado(Boolean estado) {
            this.estado = estado;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Vecino build() {
            return new Vecino(this);
        }
    }
}