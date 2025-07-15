package com.vecino.vecino.services.interfaces;

import java.util.List;

import com.vecino.vecino.entities.Nacionalidad;

public interface NacionalidadService {


    List<Nacionalidad> findAll();

    Nacionalidad findByNombreNacionalidad(String name);

}
