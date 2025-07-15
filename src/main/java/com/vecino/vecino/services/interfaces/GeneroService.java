package com.vecino.vecino.services.interfaces;

import java.util.List;

import com.vecino.vecino.entities.Genero;

public interface GeneroService {


    List<Genero> findAll();

    Genero findByNombreGenero(String nameGenero);
   

}
