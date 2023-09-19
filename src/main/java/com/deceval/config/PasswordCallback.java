package com.deceval.config;


import com.deceval.infrastructure.constants.ConstantsProperties;
import com.deceval.infrastructure.util.PropertiesLoader;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;


/**
 * Esta clase representa el manejador de passwords cuando se realizan procesos
 * de UsernameToken. El usuario y contraseña debe ser las credenciales asignadas
 * que se validarán contra el Servidor de aplicaciones
 */
public class PasswordCallback implements CallbackHandler {

    Logger logger = LoggerFactory.getLogger(PasswordCallback.class);

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        logger.info("Identificador: " + pc.getIdentifier());
        if (pc.getIdentifier() != null) {
            pc.setPassword(null);
            if (pc.getIdentifier().equals(PropertiesLoader.loadProperty(ConstantsProperties.WSSECURITY_USER.getName()))) {
                pc.setPassword(PropertiesLoader.loadProperty(ConstantsProperties.WSSECURITY_PASSWORD.getName()));
            } else {
                pc.setPassword(PropertiesLoader.loadProperty(ConstantsProperties.TOKEN_PASSWORD.getName()));
            }
        }
    }
}
