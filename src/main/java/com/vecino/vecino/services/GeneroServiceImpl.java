package com.vecino.vecino.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vecino.vecino.entities.Genero;
import com.vecino.vecino.repositories.GeneroRepository;
import com.vecino.vecino.services.interfaces.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    @Override
    public Genero findByNombreGenero(String nameGenero) {
        return generoRepository.findByNombreGenero(nameGenero)
                .orElseThrow(() -> new IllegalArgumentException("No existe el genero " + nameGenero));
    }

}
