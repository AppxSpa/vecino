package com.vecino.vecino.services;

import org.springframework.stereotype.Service;

import com.vecino.vecino.dto.PersonaDto;
import com.vecino.vecino.dto.PersonaRequest;
import com.vecino.vecino.entities.Persona;
import com.vecino.vecino.repositories.PersonaRepository;
import com.vecino.vecino.services.interfaces.EstadoCivilService;
import com.vecino.vecino.services.interfaces.GeneroService;
import com.vecino.vecino.services.interfaces.NacionalidadService;
import com.vecino.vecino.services.interfaces.PersonaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final EstadoCivilService estadoCivilService;
    private final NacionalidadService nacionalidadService;
    private final GeneroService generoService;
    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(EstadoCivilService estadoCivilService, NacionalidadService nacionalidadService,
            GeneroService generoService, PersonaRepository personaRepository) {
        this.estadoCivilService = estadoCivilService;
        this.nacionalidadService = nacionalidadService;
        this.generoService = generoService;
        this.personaRepository = personaRepository;

    }

    @Override
    public Boolean validateRut(String rut, String vrut) {
        // Asegurarse de que el RUT no sea nulo, no vacío y que el VRUT no sea nulo ni
        // vacío
        if (rut == null || vrut == null || rut.isEmpty() || vrut.isEmpty()) {
            return false;
        }

        // Validar que el RUT solo contenga dígitos
        if (!rut.matches("\\d+")) {
            return false;
        }

        // Calcular el dígito verificador basado en el RUT
        char dvCalculado = calculateVerifyDigit(rut);

        // Comparar el dígito verificador calculado con el proporcionado (ignorar
        // mayúsculas/minúsculas)
        return String.valueOf(dvCalculado).equalsIgnoreCase(vrut);
    }

    private char calculateVerifyDigit(String rutNumeros) {
        int suma = 0;
        int multiplicador = 2;

        // Iterar sobre el RUT desde el último dígito hacia el primero
        for (int i = rutNumeros.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(rutNumeros.charAt(i)) * multiplicador;
            multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
        }

        int resto = suma % 11;
        int digito = 11 - resto;

        // Retornar el dígito verificador correspondiente
        return switch (digito) {
            case 11 -> '0';
            case 10 -> 'K';
            default -> (char) (digito + '0');
        };
    }

    @Override
    public Persona createPersona(PersonaRequest personaRequest) {
        Persona persona = new Persona();

        persona.setRut(personaRequest.getRut());
        persona.setVrut(personaRequest.getVrut());
        persona.setFechaNac(personaRequest.getFechaNac());
        persona.setNombres(personaRequest.getNombres());
        persona.setPaterno(personaRequest.getPaterno());
        persona.setMaterno(personaRequest.getMaterno());
        persona.setFono(personaRequest.getFono());
        persona.setEmail(personaRequest.getEmail());
        persona.setDirId(personaRequest.getDirId());
        persona.setEstadoCivil(personaRequest.getEstadoCivil() != null
                ? estadoCivilService.findByNombreEstado(personaRequest.getEstadoCivil())
                : null);
        persona.setNacionalidad(personaRequest.getNacionalidad() != null
                ? nacionalidadService.findByNombreNacionalidad(personaRequest.getNacionalidad())
                : null);
        persona.setGenero(
                personaRequest.getGenero() != null ? generoService.findByNombreGenero(personaRequest.getGenero())
                        : null);

        return personaRepository.save(persona);
    }

    @Override
    public PersonaDto getPersona(Integer rut) {
        Persona persona = personaRepository.findByRut(rut)
                .orElseThrow(() -> new IllegalArgumentException("Persona no econtrada"));

        PersonaDto personaDto = new PersonaDto();

        personaDto.setRut(rut);
        personaDto.setVrut(persona.getVrut());
        personaDto.setNombres(persona.getNombres());
        personaDto.setPaterno(persona.getPaterno());
        personaDto.setMaterno(persona.getMaterno());
        personaDto.setFechaNac(persona.getFechaNac());
        personaDto.setEmail(persona.getEmail());
        personaDto.setEstadoCivil(persona.getNombreEstadoCivil());
        personaDto.setGenero(persona.getNombreGenero());
        personaDto.setTelefono(persona.getFono());
        personaDto.setNacionalidad(persona.getNombreNacionalidad());

        return personaDto;
    }

    @Override
    public Persona findByRut(Integer rut) {
        return personaRepository.findByRut(rut)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));
    }

    @Override
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

}
