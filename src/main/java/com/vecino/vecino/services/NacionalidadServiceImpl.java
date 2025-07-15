package com.vecino.vecino.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vecino.vecino.entities.Nacionalidad;
import com.vecino.vecino.repositories.NacionalidadRepository;
import com.vecino.vecino.services.interfaces.NacionalidadService;

@Service
public class NacionalidadServiceImpl implements NacionalidadService {

    private final NacionalidadRepository nacionalidadRepository;

    public NacionalidadServiceImpl(NacionalidadRepository nacionalidadRepository) {
        this.nacionalidadRepository = nacionalidadRepository;
    }

    @Override
    public List<Nacionalidad> findAll() {

        return nacionalidadRepository.findAll();
    }

    @Override
    public Nacionalidad findByNombreNacionalidad(String name) {
        return nacionalidadRepository.findByNombreNacionalidad(name)
                .orElseThrow(() -> new IllegalArgumentException("No existe la nacionalidad"));
    }

}
