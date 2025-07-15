package com.vecino.vecino.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vecino.vecino.entities.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNombreGenero(String nombreGenero);

    List<Genero> findAll();

}
