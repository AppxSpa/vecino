package com.vecino.vecino.mappers;

import com.vecino.vecino.dto.UpdateVecino;
import com.vecino.vecino.dto.VecinoDto;
import com.vecino.vecino.dto.VecinoResponse;
import com.vecino.vecino.entities.Vecino;

/**
 * Interfaz para el mapeo de datos relacionados con la entidad Vecino.
 * Define los métodos para convertir entre DTOs (Data Transfer Objects) y la entidad Vecino.
 * Esta abstracción permite desacoplar la lógica de negocio de la representación de datos.
 */
public interface VecinoMapper {

    /**
     * Convierte un objeto VecinoDto (usado para la creación) a una entidad Vecino.
     *
     * @param vecinoDto El DTO con los datos para crear un nuevo vecino.
     * @return Una instancia de la entidad Vecino, lista para ser procesada o persistida.
     */
    Vecino toEntity(VecinoDto vecinoDto);

    /**
     * Convierte una entidad Vecino a un objeto VecinoResponse (usado para exponer datos).
     *
     * @param vecino La entidad Vecino a convertir.
     * @return Un DTO VecinoResponse con los datos formateados para el cliente.
     */
    VecinoResponse toResponse(Vecino vecino);

    /**
     * Actualiza una entidad Vecino existente con los datos de un DTO UpdateVecino.
     *
     * @param updateDto El DTO con los datos a actualizar.
     * @param vecino    La entidad Vecino que será actualizada.
     */
    void updateEntity(UpdateVecino updateDto, Vecino vecino);
}
