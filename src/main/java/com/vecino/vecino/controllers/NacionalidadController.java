package com.vecino.vecino.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecino.vecino.entities.Nacionalidad;
import com.vecino.vecino.services.interfaces.NacionalidadService;

@RestController
@CrossOrigin(origins = "https://dev.appx.cl/")
@RequestMapping("/api/vecino")
public class NacionalidadController {

    private final NacionalidadService nacionalidadService;

    public NacionalidadController(NacionalidadService nacionalidadService) {
        this.nacionalidadService = nacionalidadService;
    }

    @GetMapping("/register/nacionalidad/list")
    public ResponseEntity<Object> getList() {
        try {
            List<Nacionalidad> nacionalidad = nacionalidadService.findAll();

            if (nacionalidad.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(nacionalidad);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
