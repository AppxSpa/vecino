package com.vecino.vecino.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vecino.vecino.entities.Nacionalidad;

@Repository
public interface NacionalidadRepository extends JpaRepository<Nacionalidad, Long> {

    Optional<Nacionalidad> findByNombreNacionalidad(String nombreNacionalidad);

    List<Nacionalidad> findAll();

}
