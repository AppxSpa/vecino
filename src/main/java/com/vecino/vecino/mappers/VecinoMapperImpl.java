package com.vecino.vecino.mappers;

import org.springframework.stereotype.Service;

import com.vecino.vecino.dto.UpdateVecino;
import com.vecino.vecino.dto.VecinoDto;
import com.vecino.vecino.dto.VecinoResponse;
import com.vecino.vecino.entities.Vecino;

import java.time.LocalDate;

/**
 * Implementación de la interfaz VecinoMapper.
 * Se encarga de las conversiones concretas entre los DTOs y la entidad Vecino.
 * Utiliza el patrón Builder para la construcción de objetos, promoviendo un código más limpio y mantenible.
 */
@Service
public class VecinoMapperImpl implements VecinoMapper {

    /**
     * {@inheritDoc}
     * Construye una entidad Vecino a partir de un VecinoDto utilizando el patrón Builder.
     * Este enfoque centraliza la lógica de creación y mejora la legibilidad.
     */
    @Override
    public Vecino toEntity(VecinoDto vecinoDto) {
        return new Vecino.Builder()
                .rut(vecinoDto.getRut())
                .vrut(vecinoDto.getVrut())
                .nombres(vecinoDto.getNombres())
                .paterno(vecinoDto.getPaterno())
                .materno(vecinoDto.getMaterno())
                .fechaNac(vecinoDto.getFechaNac())
                .fono(vecinoDto.getFono())
                .email(vecinoDto.getEmail())
                .fechaRegistro(LocalDate.now()) // Asignar la fecha de registro actual
                .estado(true) // Definir el estado inicial como activo
                .build();
    }

    /**
     * {@inheritDoc}
     * Construye un VecinoResponse a partir de una entidad Vecino utilizando el patrón Builder.
     */
    @Override
    public VecinoResponse toResponse(Vecino vecino) {
        return new VecinoResponse.Builder()
                .rut(vecino.getRut())
                .vrut(vecino.getVrut())
                .nombres(vecino.getNombres())
                .paterno(vecino.getPaterno())
                .materno(vecino.getMaterno())
                .email(vecino.getEmail())
                .build();
    }

    /**
     * {@inheritDoc}
     * Actualiza los campos de una entidad Vecino con la información de un UpdateVecino DTO.
     * Se utilizan los setters de la entidad para modificar un objeto ya existente.
     */
    @Override
    public void updateEntity(UpdateVecino updateDto, Vecino vecino) {
        vecino.setNombres(updateDto.getNombres());
        vecino.setPaterno(updateDto.getPaterno());
        vecino.setMaterno(updateDto.getMaterno());
        vecino.setFechaNac(updateDto.getFechaNac());
        vecino.setFono(updateDto.getFono());
    }
}
