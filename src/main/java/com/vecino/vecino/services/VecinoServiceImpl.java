package com.vecino.vecino.services;

import com.vecino.vecino.dto.*;
import com.vecino.vecino.entities.*;
import com.vecino.vecino.exceptions.PersonaExecptions;
import com.vecino.vecino.mappers.VecinoMapper;
import com.vecino.vecino.repositories.VecinoRepository;
import com.vecino.vecino.services.interfaces.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la gestión de Vecinos.
 * Ha sido refactorizada para delegar responsabilidades y mejorar la claridad del código.
 * - La conversión entre DTOs y Entidades se delega a `VecinoMapper`.
 * - La construcción de objetos se realiza mediante el patrón Builder.
 * - Los nombres de las variables se han mejorado para seguir las prácticas de Clean Code.
 */
@Service
public class VecinoServiceImpl implements VecinoService {

    // --- DEPENDENCIAS ---
    private final VecinoRepository vecinoRepository;
    private final PersonaService personaService;
    private final UsuarioService usuarioService;
    private final GeneroService generoService;
    private final NacionalidadService nacionalidadService;
    private final EstadoCivilService estadoCivilService;
    private final ApiDireccionService apiDireccionService;
    private final ApiGetDireccionService apiGetDireccionService;
    private final VecinoMapper vecinoMapper; // Dependencia del Mapper

    public VecinoServiceImpl(
            VecinoRepository vecinoRepository,
            PersonaService personaService,
            UsuarioService usuarioService,
            GeneroService generoService,
            NacionalidadService nacionalidadService,
            EstadoCivilService estadoCivilService,
            ApiDireccionService apiDireccionService,
            ApiGetDireccionService apiGetDireccionService,
            VecinoMapper vecinoMapper) { // Inyección del Mapper
        this.vecinoRepository = vecinoRepository;
        this.personaService = personaService;
        this.usuarioService = usuarioService;
        this.generoService = generoService;
        this.nacionalidadService = nacionalidadService;
        this.estadoCivilService = estadoCivilService;
        this.apiDireccionService = apiDireccionService;
        this.apiGetDireccionService = apiGetDireccionService;
        this.vecinoMapper = vecinoMapper;
    }

    /**
     * Crea un nuevo Vecino en el sistema.
     * El proceso ha sido refactorizado para una mayor claridad y separación de responsabilidades.
     *
     * @param createVecinoRequest DTO con la información para la creación del vecino.
     * @return La entidad Vecino persistida.
     */
    @Override
    @Transactional
    public Vecino createVecino(VecinoDto createVecinoRequest) {
        // 1. Mapeo inicial de DTO a Entidad usando el Mapper y Builder
        Vecino vecino = vecinoMapper.toEntity(createVecinoRequest);

        // 2. Enriquecimiento de la entidad con objetos relacionados
        EstadoCivil estadoCivil = estadoCivilService.findByNombreEstado(createVecinoRequest.getEstadoCivil());
        vecino.setEstadoCivil(estadoCivil);

        Genero genero = generoService.findByNombreGenero(createVecinoRequest.getGenero());
        vecino.setGenero(genero);

        Nacionalidad nacionalidad = nacionalidadService.findByNombreNacionalidad(createVecinoRequest.getNacionalidad());
        vecino.setNacionalidad(nacionalidad);

        // 3. Persistencia inicial para obtener el ID del vecino
        vecino = vecinoRepository.save(vecino);

        // 4. Creación de la dirección a través de una API externa
        DireccionRequest direccionRequest = new DireccionRequest(createVecinoRequest.getCalle(),
                createVecinoRequest.getNroCalle(), createVecinoRequest.getComuna(), 1D, 2D, createVecinoRequest.getAclaratoria());
        CreateDireccionResponse direccionResponse = apiDireccionService.createDireccion(direccionRequest);

        // 5. Actualización del vecino con el ID de la dirección y persistencia final
        vecino.setDirId(direccionResponse.getDirId());
        vecino = vecinoRepository.save(vecino);

        // 6. Creación del usuario asociado
        usuarioService.createUser(createVecinoRequest.getRut().toString(), createVecinoRequest.getPassword());

        return vecino;
    }

    /**
     * Obtiene la información de un Vecino por su RUT.
     *
     * @param rut El RUT del vecino a buscar.
     * @return Un DTO `VecinoResponse` con la información completa.
     */
    @Override
    public VecinoResponse getVecinoByRut(Integer rut) {
        // 1. Búsqueda del vecino
        Vecino vecino = vecinoRepository.findByRut(rut)
                .orElseThrow(() -> new PersonaExecptions("No se encontro el vecino con rut " + rut));

        // 2. Mapeo base de Entidad a DTO de respuesta
        VecinoResponse vecinoResponse = vecinoMapper.toResponse(vecino);

        // 3. Enriquecimiento del DTO con información de la dirección
        DireccionResponse direccionResponse = apiGetDireccionService.getDireccionById(vecino.getDirId());

        // 4. Construcción final del DTO de respuesta usando un Builder
        // Esto permite mantener la inmutabilidad del DTO original y añadir la información de la dirección.
        if (direccionResponse != null) {
            return new VecinoResponse.Builder()
                    .rut(vecinoResponse.getRut())
                    .vrut(vecinoResponse.getVrut())
                    .nombres(vecinoResponse.getNombres())
                    .paterno(vecinoResponse.getPaterno())
                    .materno(vecinoResponse.getMaterno())
                    .email(vecinoResponse.getEmail())
                    .calle(direccionResponse.getNombreCalle())
                    .nro(direccionResponse.getNroCalle())
                    .adicional(direccionResponse.getAdicional())
                    .comuna(direccionResponse.getComuna())
                    .provincia(direccionResponse.getProvincia())
                    .region(direccionResponse.getRegion())
                    .build();
        }

        return vecinoResponse; // Retorna la respuesta sin dirección si no se encuentra
    }

    /**
     * Actualiza la información de un Vecino existente.
     *
     * @param rut El RUT del vecino a actualizar.
     * @param updateVecinoRequest DTO con los nuevos datos.
     * @return La entidad Vecino actualizada.
     */
    @Override
    @Transactional
    public Vecino updateInfo(Integer rut, UpdateVecino updateVecinoRequest) {
        // 1. Búsqueda de la persona y el vecino
        Persona persona = personaService.findByRut(rut);
        Vecino vecino = getVecinoByIdPersona(persona.getId());

        // 2. Actualización del email si ha cambiado (operación transaccional separada)
        if (!persona.getEmail().equals(updateVecinoRequest.getEmail())) {
            updateEmail(rut, updateVecinoRequest.getEmail());
        }

        // 3. Mapeo de los campos básicos desde el DTO a la entidad
        vecinoMapper.updateEntity(updateVecinoRequest, vecino);

        // 4. Actualización de las relaciones
        EstadoCivil estadoCivil = estadoCivilService.findByNombreEstado(updateVecinoRequest.getEstadoCivil());
        vecino.setEstadoCivil(estadoCivil);

        Genero genero = generoService.findByNombreGenero(updateVecinoRequest.getGenero());
        vecino.setGenero(genero);

        Nacionalidad nacionalidad = nacionalidadService.findByNombreNacionalidad(updateVecinoRequest.getNacionalidad());
        vecino.setNacionalidad(nacionalidad);

        // 5. Actualización de la dirección
        apiDireccionService.createDireccion(new DireccionRequest(updateVecinoRequest.getCalle(),
                updateVecinoRequest.getNroCalle(), updateVecinoRequest.getComuna(), 1D, 2D, updateVecinoRequest.getAclaratoria()));

        // 6. Persistencia de los cambios
        return vecinoRepository.save(vecino);
    }

    /**
     * Método transaccional privado para actualizar el email de un usuario.
     */
    @Transactional
    private void updateEmail(Integer rut, String nuevoEmail) {
        Persona persona = personaService.findByRut(rut);
        persona.setEmail(nuevoEmail);
        personaService.save(persona);
        usuarioService.changeMail(rut, nuevoEmail);
    }

    /**
     * Busca un Vecino por el ID de la Persona asociada.
     */
    private Vecino getVecinoByIdPersona(Long personaId) {
        return vecinoRepository.findById(personaId)
                .orElseThrow(() -> new EntityNotFoundException("Vecino no encontrado con ID de persona: " + personaId));
    }
}
