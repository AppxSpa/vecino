package com.vecino.vecino.services;

import org.springframework.stereotype.Service;

import com.vecino.vecino.services.interfaces.ApiUserService;
import com.vecino.vecino.services.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final ApiUserService apiUserService;

    public UsuarioServiceImpl(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;

    }

    @Override
    public void createUser(String username, String password) {

        apiUserService.createUserApi(username, password);
    }

}
