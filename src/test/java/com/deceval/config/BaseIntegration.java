package com.deceval.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;

public class BaseIntegration {

    protected static  final String HOST = "http://localhost:%s/";

    @Autowired
    protected  WebTestClient webTestClient;

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> T loadFileConfig(String filename, Class<T> clazz) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            String str = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            if (clazz.equals(String.class)) {
                return (T) str;
            }
            return objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            //Assertions.fail(e.getMessage());
            return null;
        }
    }

    protected StatusAssertions statusAssertionsWebClient(String path, String requestBody, HttpMethod method){
        webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(30000)).build();
        return webTestClient.method(method)
                .uri(path)
                .contentType(MediaType.APPLICATION_XML)
                .body(BodyInserters.fromValue(requestBody))
                .exchange().expectStatus();
    }

}
