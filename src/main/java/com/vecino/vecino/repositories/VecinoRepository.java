package com.vecino.vecino.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vecino.vecino.entities.Vecino;

@Repository
public interface VecinoRepository extends JpaRepository<Vecino, Long> {

    Optional<Vecino> findByRut(Integer rut);

    Optional<Vecino> findById(Long id);

}
