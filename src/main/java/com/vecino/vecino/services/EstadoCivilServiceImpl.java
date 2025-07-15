package com.vecino.vecino.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vecino.vecino.entities.EstadoCivil;
import com.vecino.vecino.repositories.EstadoCivilRepository;
import com.vecino.vecino.services.interfaces.EstadoCivilService;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;

    public EstadoCivilServiceImpl(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    @Override
    public List<EstadoCivil> findAll() {
        return estadoCivilRepository.findAll();
    }

    @Override
    public EstadoCivil findByNombreEstado(String name) {
        return estadoCivilRepository.findByNombreEstado(name)
                .orElseThrow(() -> new IllegalArgumentException("Estado civil no encontrado " + name));
    }

}
