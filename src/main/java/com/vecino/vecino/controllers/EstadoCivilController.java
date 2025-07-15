package com.vecino.vecino.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecino.vecino.entities.EstadoCivil;
import com.vecino.vecino.services.interfaces.EstadoCivilService;

@RestController
@CrossOrigin(origins = "https://dev.appx.cl/")
@RequestMapping("/api/vecino")
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    public EstadoCivilController(EstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    @GetMapping("/register/estado-civil/list")
    public ResponseEntity<Object> getList() {
        try {
            List<EstadoCivil> estadoCivil = estadoCivilService.findAll();

            if (estadoCivil.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(estadoCivil);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
