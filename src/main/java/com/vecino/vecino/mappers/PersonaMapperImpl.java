package com.vecino.vecino.mappers;

import com.vecino.vecino.dto.PersonaDto;
import com.vecino.vecino.entities.Persona;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz PersonaMapper.
 * Se encarga de la conversión de la entidad Persona a PersonaDto.
 */
@Service
public class PersonaMapperImpl implements PersonaMapper {

    /**
     * {@inheritDoc}
     * Utiliza el patrón Builder de PersonaDto para una construcción limpia y segura.
     */
    @Override
    public PersonaDto toDto(Persona persona) {
        if (persona == null) {
            return null;
        }

        return new PersonaDto.Builder()
                .rut(persona.getRut())
                .vrut(persona.getVrut())
                .nombres(persona.getNombres())
                .paterno(persona.getPaterno())
                .materno(persona.getMaterno())
                .fechaNac(persona.getFechaNac())
                .email(persona.getEmail())
                .telefono(persona.getFono())
                .estadoCivil(persona.getEstadoCivil() != null ? persona.getEstadoCivil().getNombreEstado() : null)
                .genero(persona.getGenero() != null ? persona.getGenero().getNombreGenero() : null)
                .nacionalidad(persona.getNacionalidad() != null ? persona.getNacionalidad().getNombreNacionalidad() : null)
                .build();
    }
}
