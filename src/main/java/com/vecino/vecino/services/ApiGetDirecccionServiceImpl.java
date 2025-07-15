package com.vecino.vecino.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vecino.vecino.config.ApiProperties;
import com.vecino.vecino.dto.DireccionResponse;
import com.vecino.vecino.services.interfaces.ApiGetDireccionService;

import reactor.core.publisher.Mono;

@Service
public class ApiGetDirecccionServiceImpl implements ApiGetDireccionService {

    private final WebClient webClientDireccion;

    public ApiGetDirecccionServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClientDireccion = webClientBuilder.baseUrl(apiProperties.getDireccionUrl()).build();
    }

    @Override
    public DireccionResponse getDireccionById(Long id) {
        return webClientDireccion.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/direcciones")
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .bodyToMono(DireccionResponse.class)
                .onErrorResume(e -> Mono.empty())
                .block();
    }
}
