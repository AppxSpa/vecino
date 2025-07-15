package com.vecino.vecino.services;

import org.springframework.stereotype.Service;

import com.vecino.vecino.services.interfaces.ApiMailService;
import com.vecino.vecino.services.interfaces.ApiUserService;
import com.vecino.vecino.services.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final ApiUserService apiUserService;
    private final ApiMailService apiMailService;

    public UsuarioServiceImpl(ApiUserService apiUserService,
            ApiMailService apiMailServiceImpl) {
        this.apiUserService = apiUserService;
        this.apiMailService = apiMailServiceImpl;

    }

    @Override
    public void createUser(String username, String password) {

        apiUserService.createUserApi(username, password);
    }

    @Override
    public void changeMail(Integer rut, String email) {
        apiMailService.changeMail(rut, email);
    }

}
