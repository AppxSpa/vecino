package com.vecino.vecino.services.interfaces;

public interface UsuarioService {

    void createUser(String username, String password);

    void changeMail(Integer rut, String email);

}
