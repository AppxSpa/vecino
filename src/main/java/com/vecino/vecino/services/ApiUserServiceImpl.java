package com.vecino.vecino.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vecino.vecino.config.ApiProperties;
import com.vecino.vecino.dto.UsuarioRequest;
import com.vecino.vecino.dto.UsuarioResponse;
import com.vecino.vecino.services.interfaces.ApiUserService;

@Service
public class ApiUserServiceImpl implements ApiUserService {

    private final WebClient webClient;

    public ApiUserServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClient = webClientBuilder.baseUrl(apiProperties.getUsuarioUrl()).build();
    }

    @Override
    public void createUserApi(String username, String password) {
        UsuarioRequest request = new UsuarioRequest(username, password);

        webClient.post() 
                .uri("/api/auth/usuarios/create") 
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UsuarioResponse.class)
                .block(); 
    }

}
