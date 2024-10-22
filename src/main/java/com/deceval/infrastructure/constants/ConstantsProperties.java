package com.deceval.infrastructure.constants;

public enum ConstantsProperties {
    SSL_KEYSTORE_FILE("co.com.integracion.ssl.keystore.file"),
    SSL_KEYSTORE_PASSWORD("co.com.integracion.ssl.keystore.password"),
    CRYPTO_PROVIDER("co.com.integracion.ws.security.crypto.provider"),
    CRYPTO_FILE("co.com.integracion.ws.security.crypto.merlin.keystore.file"),
    CRYPTO_TYPE("co.com.integracion.ws.security.crypto.merlin.keystore.type"),
    WSSECURITY_USER("co.com.integracion.wssecurity.keystore.user"),
    WSSECURITY_PASSWORD("co.com.integracion.wssecurity.keystore.password"),
    TOKEN_USERNAME("co.com.integracion.token.username"),
    TOKEN_PASSWORD("co.com.integracion.token.password"),
    SDL_ENDPOINT("co.com.integracion.sdl.enpoint.address");

    private final String name;

    ConstantsProperties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
