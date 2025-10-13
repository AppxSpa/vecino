package com.vecino.vecino.services;

import com.vecino.vecino.dto.PersonaDto;
import com.vecino.vecino.dto.PersonaRequest;
import com.vecino.vecino.entities.EstadoCivil;
import com.vecino.vecino.entities.Genero;
import com.vecino.vecino.entities.Nacionalidad;
import com.vecino.vecino.entities.Persona;
import com.vecino.vecino.mappers.PersonaMapper;
import com.vecino.vecino.repositories.PersonaRepository;
import com.vecino.vecino.services.interfaces.EstadoCivilService;
import com.vecino.vecino.services.interfaces.GeneroService;
import com.vecino.vecino.services.interfaces.NacionalidadService;
import com.vecino.vecino.services.interfaces.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la gestión de Personas.
 * Completamente refactorizada para usar Mappers y el patrón Builder.
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final EstadoCivilService estadoCivilService;
    private final NacionalidadService nacionalidadService;
    private final GeneroService generoService;
    private final PersonaMapper personaMapper; // Inyección del Mapper

    public PersonaServiceImpl(PersonaRepository personaRepository,
                              EstadoCivilService estadoCivilService,
                              NacionalidadService nacionalidadService,
                              GeneroService generoService,
                              PersonaMapper personaMapper) { // Mapper en el constructor
        this.personaRepository = personaRepository;
        this.estadoCivilService = estadoCivilService;
        this.nacionalidadService = nacionalidadService;
        this.generoService = generoService;
        this.personaMapper = personaMapper;
    }

    @Override
    public Boolean validateRut(String rut, String vrut) {
        if (rut == null || vrut == null || rut.isEmpty() || vrut.isEmpty()) {
            return false;
        }
        if (!rut.matches("\\d+")) {
            return false;
        }
        char dvCalculado = calculateVerifyDigit(rut);
        return String.valueOf(dvCalculado).equalsIgnoreCase(vrut);
    }

    private char calculateVerifyDigit(String rutNumeros) {
        int suma = 0;
        int multiplicador = 2;
        for (int i = rutNumeros.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(rutNumeros.charAt(i)) * multiplicador;
            multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
        }
        int resto = suma % 11;
        int digito = 11 - resto;
        return switch (digito) {
            case 11 -> '0';
            case 10 -> 'K';
            default -> (char) (digito + '0');
        };
    }

    @Override
    public Persona createPersona(PersonaRequest personaRequest) {
        EstadoCivil estadoCivil = personaRequest.getEstadoCivil() != null
                ? estadoCivilService.findByNombreEstado(personaRequest.getEstadoCivil())
                : null;
        Nacionalidad nacionalidad = personaRequest.getNacionalidad() != null
                ? nacionalidadService.findByNombreNacionalidad(personaRequest.getNacionalidad())
                : null;
        Genero genero = personaRequest.getGenero() != null
                ? generoService.findByNombreGenero(personaRequest.getGenero())
                : null;

        Persona persona = new Persona.PersonaBuilder()
                .rut(personaRequest.getRut())
                .vrut(personaRequest.getVrut())
                .fechaNac(personaRequest.getFechaNac())
                .nombres(personaRequest.getNombres())
                .paterno(personaRequest.getPaterno())
                .materno(personaRequest.getMaterno())
                .fono(personaRequest.getFono())
                .email(personaRequest.getEmail())
                .dirId(personaRequest.getDirId())
                .estadoCivil(estadoCivil)
                .nacionalidad(nacionalidad)
                .genero(genero)
                .build();

        return personaRepository.save(persona);
    }

    /**
     * Obtiene una persona por su RUT y la convierte a DTO.
     * Refactorizado para delegar la conversión al PersonaMapper.
     *
     * @param rut El RUT de la persona a buscar.
     * @return El DTO con la información de la persona.
     */
    @Override
    public PersonaDto getPersona(Integer rut) {
        Persona persona = personaRepository.findByRut(rut)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
        
        // Delegación de la conversión al mapper
        return personaMapper.toDto(persona);
    }

    @Override
    public Persona findByRut(Integer rut) {
        return personaRepository.findByRut(rut)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con rut: " + rut));
    }

    @Override
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }
}
