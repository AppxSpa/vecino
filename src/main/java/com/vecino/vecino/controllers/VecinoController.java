package com.vecino.vecino.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecino.vecino.dto.VecinoDto;
import com.vecino.vecino.dto.VecinoResponse;
import com.vecino.vecino.entities.Vecino;
import com.vecino.vecino.exceptions.PersonaExecptions;
import com.vecino.vecino.services.interfaces.PersonaService;
import com.vecino.vecino.services.interfaces.VecinoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vecino/register")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class VecinoController {

    private final VecinoService vecinoService;
    private final PersonaService personaService;

    public VecinoController(VecinoService vecinoService, PersonaService personaService) {
        this.vecinoService = vecinoService;
        this.personaService = personaService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVecino(@Valid @RequestBody VecinoDto vecinoDto, BindingResult result) {

        if (result.hasErrors()) {
            return validation(result);
        }

        if (Boolean.FALSE.equals(personaService.validateRut(vecinoDto.getRut().toString(), vecinoDto.getVrut()))) {

            HashMap<String, String> body = new HashMap<>();
            body.put("message", "Rut invalido");

            return ResponseEntity.badRequest().body(body);

        }

        try {

            Vecino vecino = vecinoService.createVecino(vecinoDto);
            return ResponseEntity.ok(vecino);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el vecino: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/{rut}")
    public ResponseEntity<Object> getVecinoByRut(@PathVariable Integer rut) {
        try {
            VecinoResponse vecino = vecinoService.getVecinoByRut(rut);

            return ResponseEntity.ok(vecino);
        } catch (PersonaExecptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El RUT proporcionado no es válido: " + rut);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al buscar el vecino: " + e.getMessage());
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(e -> errors.put(e.getField(), "El campo" + " " + e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);

    }

}
