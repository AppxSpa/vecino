package com.vecino.vecino.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Vecino extends Persona {


    private LocalDate fechaRegistro;
    private Boolean estado;

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
