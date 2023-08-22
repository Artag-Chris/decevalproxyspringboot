package com.deceval.infrastructure.constants;

public final class ConstantesFirmaDigital {

    // Nombre del archivo de configuración de propiedades de firmado
    public final static String ARCHIVO_CONFIGURACION = "crypto.properties";
    // Namespace correspondiente al header de Timestamp
    public final static String TIMESTAMP_SIGN_PART = "{Content}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp";
    // Namespace correspondiente al header de UsernameToken
    public final static String UNT_SIGN_PART = "{Content}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd}UsernameToken";
    // Namespace correspondiente al header del SOAPBody
    public final static String BODY_SIGN_PART = "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body";
    // Namespace correspondiente al header de Binary Security Token
    public final static String BST_SIGN_PART = "{Content}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd}BinarySecurityToken";
    // Represneta la constante de todas las partes
    public final static String SIGN_PARTS = TIMESTAMP_SIGN_PART + ";" + UNT_SIGN_PART + ";" + BODY_SIGN_PART + ";" + BST_SIGN_PART;
}
