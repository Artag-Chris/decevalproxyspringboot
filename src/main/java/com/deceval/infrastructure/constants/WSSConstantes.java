package com.deceval.infrastructure.constants;

public enum WSSConstantes {

    FIRMADO("Signature"),
    TIMESTAMP("Timestamp"),
    USERNAMETOKEN("UsernameToken");

    private final String valor;

    WSSConstantes(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
