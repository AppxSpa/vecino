package com.vecino.vecino.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vecino.vecino.config.ApiProperties;
import com.vecino.vecino.dto.CreateDireccionResponse;
import com.vecino.vecino.dto.DireccionRequest;
import com.vecino.vecino.services.interfaces.ApiDireccionService;

@Service
public class ApiDireccionServiceImpl implements ApiDireccionService {

    private final WebClient webClient;

    public ApiDireccionServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClient = webClientBuilder.baseUrl(apiProperties.getDireccionUrl()).build();
    }

    @Override
    public CreateDireccionResponse createDireccion(DireccionRequest request) {

        CreateDireccionResponse response = null;

        try {
            response = webClient.post()
                    .uri("/api/direcciones")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(CreateDireccionResponse.class)

                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }
}
