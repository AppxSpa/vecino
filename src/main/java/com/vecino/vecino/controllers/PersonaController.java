package com.vecino.vecino.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecino.vecino.dto.PersonaDto;
import com.vecino.vecino.dto.PersonaRequest;
import com.vecino.vecino.entities.Persona;
import com.vecino.vecino.services.interfaces.PersonaService;

@RestController
@CrossOrigin(origins = "https://dev.appx.cl/")
@RequestMapping("/api/vecino/persona")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPersona(@RequestBody PersonaRequest personaRequest) {

        try {

            if (Boolean.FALSE
                    .equals(personaService.validateRut(personaRequest.getRut().toString(), personaRequest.getVrut()))) {

                HashMap<String, String> body = new HashMap<>();
                body.put("message", "Rut invalido");

                return ResponseEntity.badRequest().body(body);

            }

            Persona person = personaService.createPersona(personaRequest);
            return ResponseEntity.ok(person);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la persona: " + e.getMessage());
        }

    }

    @GetMapping("{rut}")
    public ResponseEntity<Object> getPersonaByRut(@PathVariable Integer rut) {

        try {
            PersonaDto personaDto = personaService.getPersona(rut);
            return ResponseEntity.ok(personaDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error el leer la persona " + e.getMessage());
        }
    }

}
