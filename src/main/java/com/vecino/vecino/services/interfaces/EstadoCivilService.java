package com.vecino.vecino.services.interfaces;

import java.util.List;

import com.vecino.vecino.entities.EstadoCivil;

public interface EstadoCivilService {


    List<EstadoCivil> findAll() ;

    EstadoCivil findByNombreEstado(String name);

}
