package com.vecino.vecino.services;

import org.springframework.stereotype.Service;

import com.vecino.vecino.dto.CreateDireccionResponse;
import com.vecino.vecino.dto.DireccionRequest;
import com.vecino.vecino.dto.DireccionResponse;
import com.vecino.vecino.dto.UpdateVecino;
import com.vecino.vecino.dto.VecinoDto;
import com.vecino.vecino.dto.VecinoResponse;
import com.vecino.vecino.entities.EstadoCivil;
import com.vecino.vecino.entities.Genero;
import com.vecino.vecino.entities.Nacionalidad;
import com.vecino.vecino.entities.Persona;
import com.vecino.vecino.entities.Vecino;
import com.vecino.vecino.exceptions.PersonaExecptions;
import com.vecino.vecino.repositories.VecinoRepository;
import com.vecino.vecino.services.interfaces.ApiDireccionService;
import com.vecino.vecino.services.interfaces.ApiGetDireccionService;
import com.vecino.vecino.services.interfaces.EstadoCivilService;
import com.vecino.vecino.services.interfaces.GeneroService;
import com.vecino.vecino.services.interfaces.NacionalidadService;
import com.vecino.vecino.services.interfaces.PersonaService;
import com.vecino.vecino.services.interfaces.UsuarioService;
import com.vecino.vecino.services.interfaces.VecinoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VecinoServiceImpl implements VecinoService {

    private final GeneroService generoService;
    private final NacionalidadService nacionalidadService;
    private final EstadoCivilService estadoCivilService;
    private final VecinoRepository vecinoRepository;
    private final UsuarioService usuarioService;
    private final ApiDireccionService apiDireccionService;
    private final PersonaService personaService;
    private final ApiGetDireccionService apiGetDireccionService;

    public VecinoServiceImpl(
            GeneroService generoService,
            NacionalidadService nacionalidadService,
            EstadoCivilService estadoCivilService,
            VecinoRepository vecinoRepository,
            UsuarioService usuarioService,
            ApiDireccionService apiDireccionService,
            PersonaService personaService,
            ApiGetDireccionService apiGetDireccionService) {
        this.vecinoRepository = vecinoRepository;
        this.usuarioService = usuarioService;
        this.apiDireccionService = apiDireccionService;
        this.generoService = generoService;
        this.nacionalidadService = nacionalidadService;
        this.estadoCivilService = estadoCivilService;
        this.personaService = personaService;
        this.apiGetDireccionService = apiGetDireccionService;

    }

    @Override
    public Vecino createVecino(VecinoDto vecinoDto) {
        Vecino vecino = mapVecinoDto(vecinoDto);

        EstadoCivil estadoCivil = estadoCivilService.findByNombreEstado(vecinoDto.getEstadoCivil());
        vecino.setEstadoCivil(estadoCivil);

        Genero genero = generoService.findByNombreGenero(vecinoDto.getGenero());
        vecino.setGenero(genero);

        Nacionalidad nacionalidad = nacionalidadService.findByNombreNacionalidad(vecinoDto.getNacionalidad());
        vecino.setNacionalidad(nacionalidad);

        vecino = vecinoRepository.save(vecino);

        CreateDireccionResponse direccionResponse = apiDireccionService
                .createDireccion(new DireccionRequest(vecinoDto.getCalle(),
                        vecinoDto.getNroCalle(), vecinoDto.getComuna(), 1D, 2D, vecinoDto.getAclaratoria()));


        vecino.setDirId(direccionResponse.getDirId());

        vecino = vecinoRepository.save(vecino);

        usuarioService.createUser(vecinoDto.getRut().toString(), vecinoDto.getPassword());

        return vecino;
    }

    @Override
    public VecinoResponse getVecinoByRut(Integer rut) {

        Vecino vecino = vecinoRepository.findByRut(rut)
                .orElseThrow(() -> new PersonaExecptions("No se encontro el rut " + rut));

        return convertToVecinoResponse(vecino);

    }

    private VecinoResponse convertToVecinoResponse(Vecino vecino) {

        VecinoResponse dto = new VecinoResponse();

        dto.setRut(vecino.getRut());
        dto.setVrut(vecino.getVrut());
        dto.setNombres(vecino.getNombres());
        dto.setPaterno(vecino.getPaterno());
        dto.setMaterno(vecino.getMaterno());
        dto.setEmail(vecino.getEmail());

        DireccionResponse direccionResponse = apiGetDireccionService.getDireccionById(vecino.getDirId());

        if (direccionResponse != null) {
            dto.setCalle(direccionResponse.getNombreCalle());
            dto.setNro(direccionResponse.getNroCalle());
            dto.setAdicional(direccionResponse.getAdicional());
            dto.setComuna(direccionResponse.getComuna());
            dto.setRegion(direccionResponse.getRegion());
            dto.setProvincia(direccionResponse.getProvincia());

        }

        return dto;

    }

    @Override
    public Vecino updateInfo(Integer rut, UpdateVecino updateVecino) {
        Persona persona = personaService.findByRut(rut);

        // Buscar al vecino existente
        Vecino vecino = getVecinoByIdPersona(persona);

        // Actualizar campos básicos
        vecino.setNombres(updateVecino.getNombres());
        vecino.setPaterno(updateVecino.getPaterno());
        vecino.setMaterno(updateVecino.getMaterno());
        vecino.setFechaNac(updateVecino.getFechaNac());
        vecino.setFono(updateVecino.getFono());

        if (!persona.getEmail().equals(updateVecino.getEmail())) {
            updateEmail(rut, updateVecino.getEmail());
        }

        // Actualizar relaciones con validaciones
        EstadoCivil estadoCivil = estadoCivilService.findByNombreEstado(updateVecino.getEstadoCivil());
        vecino.setEstadoCivil(estadoCivil);

        Genero genero = generoService.findByNombreGenero(updateVecino.getGenero());
        vecino.setGenero(genero);

        Nacionalidad nacionalidad = nacionalidadService.findByNombreNacionalidad(updateVecino.getNacionalidad());
        vecino.setNacionalidad(nacionalidad);

        // Manejo de calle y dirección
        apiDireccionService.createDireccion(new DireccionRequest(updateVecino.getCalle(),
                updateVecino.getNroCalle(), updateVecino.getComuna(), 1D, 2D, updateVecino.getAclaratoria()));

        // Guardar cambios
        vecinoRepository.save(vecino);

        // Convertir a DTO y retornar
        return vecino;
    }

    @Transactional
    private void updateEmail(Integer rut, String email) {

        Persona persona = personaService.findByRut(rut);

        persona.setEmail(email);

        personaService.save(persona);

        usuarioService.changeMail(rut, email);

    }

    private Vecino mapVecinoDto(VecinoDto vecinoDto) {

        Vecino vecino = new Vecino();

        vecino.setRut(vecinoDto.getRut());
        vecino.setVrut(vecinoDto.getVrut());
        vecino.setNombres(vecinoDto.getNombres());
        vecino.setPaterno(vecinoDto.getPaterno());
        vecino.setMaterno(vecinoDto.getMaterno());
        vecino.setFechaNac(vecinoDto.getFechaNac());
        vecino.setFono(vecinoDto.getFono());
        vecino.setEmail(vecinoDto.getEmail());

        return vecino;

    }

    private Vecino getVecinoByIdPersona(Persona persona) {

        return vecinoRepository.findById(persona.getId())
                .orElseThrow(() -> new EntityNotFoundException("Vecino no encontrado con RUT: " + persona.getRut()));

    }
}