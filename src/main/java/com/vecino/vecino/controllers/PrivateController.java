package com.vecino.vecino.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecino.vecino.dto.UpdateVecino;
import com.vecino.vecino.entities.Vecino;
import com.vecino.vecino.services.interfaces.VecinoService;

@RestController
@RequestMapping("/api/vecino/private")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class PrivateController {

    private final com.vecino.vecino.services.interfaces.VecinoService vecinoService;

    public PrivateController(VecinoService vecinoService) {
        this.vecinoService = vecinoService;
    }

    @PutMapping("/update/{rut}")
    public Vecino updateInfo(@PathVariable Integer rut, @RequestBody UpdateVecino updateVecino) {

        return vecinoService.updateInfo(rut, updateVecino);

    }

}
