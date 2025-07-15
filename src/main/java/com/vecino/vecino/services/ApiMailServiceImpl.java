package com.vecino.vecino.services;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vecino.vecino.config.ApiProperties;
import com.vecino.vecino.dto.MailRequest;
import com.vecino.vecino.exceptions.MailExceptions;
import com.vecino.vecino.services.interfaces.ApiMailService;

import reactor.core.publisher.Mono;

@Service
public class ApiMailServiceImpl implements ApiMailService {

    private final WebClient webClient;

    public ApiMailServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClient = webClientBuilder.baseUrl(apiProperties.getUsuarioUrl()).build();
    }

    @Override
    public void changeMail(Integer rut, String email) {
        MailRequest request = new MailRequest(rut, email);

        try {
            webClient.post()
                    .uri("/api/auth/usuarios/change-mail")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError,
                            response -> response.bodyToMono(String.class)
                                    .flatMap(error -> Mono.error(new RuntimeException("Error en la API: " + error))))
                    .bodyToMono(Void.class) //
                    .block();

        } catch (MailExceptions e) {
            throw new MailExceptions("Error al cambiar correo  ");
        }
    }

}
