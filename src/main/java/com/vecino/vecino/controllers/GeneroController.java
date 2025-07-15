package com.vecino.vecino.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecino.vecino.entities.Genero;
import com.vecino.vecino.services.interfaces.GeneroService;

@RestController
@CrossOrigin(origins = "https://dev.appx.cl/")
@RequestMapping("/api/vecino")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {

        this.generoService = generoService;
    }

    @GetMapping("/register/genero/list")
    public ResponseEntity<Object> getList() {
        try {
            List<Genero> genero = generoService.findAll();

            if (genero.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(genero);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
