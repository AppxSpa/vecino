package com.vecino.vecino.mappers;

import com.vecino.vecino.dto.PersonaDto;
import com.vecino.vecino.entities.Persona;

/**
 * Interfaz para el mapeo de datos relacionados con la entidad Persona.
 * Define el contrato para convertir la entidad Persona a su DTO correspondiente.
 */
public interface PersonaMapper {

    /**
     * Convierte una entidad Persona a un objeto PersonaDto.
     *
     * @param persona La entidad a convertir.
     * @return Un DTO PersonaDto con los datos listos para ser expuestos.
     */
    PersonaDto toDto(Persona persona);

}
