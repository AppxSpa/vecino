package com.vecino.vecino.services;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.vecino.vecino.dto.VecinoCreatedEvent;
import com.vecino.vecino.services.interfaces.UsuarioService;

public class VecinoEventListener {

    private final UsuarioService usuarioService;

    public VecinoEventListener(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleVecinoCreated(VecinoCreatedEvent event) {
        usuarioService.createUser(event.rut(), event.password());
    }

}
