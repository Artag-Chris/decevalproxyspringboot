package com.deceval.infrastructure.client;

import com.deceval.config.PasswordCallback;
import com.deceval.infrastructure.constants.ConstantesFirmaDigital;
import com.deceval.infrastructure.constants.ConstantsProperties;
import com.deceval.infrastructure.constants.WSSConstantes;
import com.deceval.infrastructure.util.PropertiesLoader;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Properties;

/**
 * Esta clase se encarga de toda la configuracion de ssl, crypto, wss4 y ruta de acceso a los servicios wsdl
 * @author inerjanuerBernate
 */
@Configuration
public class ProxyServiceClient {

    Logger LOGGER = LoggerFactory.getLogger(ProxyServiceClient.class);

    public <T> T getService(Class<T> serviceClass){
        LOGGER.info("Configuracion System");
        LOGGER.info("Getting Connection WSDL ...");
        JaxWsProxyFactoryBean proxy = new JaxWsProxyFactoryBean();
        proxy.setAddress(PropertiesLoader.loadProperty(ConstantsProperties.SDL_ENDPOINT.getName()));
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        proxy.getFeatures().add(loggingFeature);
        proxy.getOutInterceptors().add(wss4JOut());
        proxy.getOutFaultInterceptors().add(wss4JOut());
        proxy.setServiceClass(serviceClass);
        @SuppressWarnings("unchecked")
        T newClient = (T) proxy.create();
        Client client = ClientProxy.getClient(newClient);
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            setupSsl(sslContext);
            TLSClientParameters tls = new TLSClientParameters();
            tls.setSSLSocketFactory(sslContext.getSocketFactory());
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy clientPolicy = new HTTPClientPolicy();
            clientPolicy.setConnectionTimeout(10000);
            clientPolicy.setReceiveTimeout(10000);
            conduit.setClient(clientPolicy);
            conduit.setTlsClientParameters(tls);
        }catch (Exception e){
            LOGGER.error(e.getClass().getName());
            LOGGER.error(e.getCause().getMessage());
            LOGGER.error(e.getMessage());
        }
        return newClient;
    }

    public WSS4JOutInterceptor wss4JOut() {
        return new WSS4JOutInterceptor(wss4jProperties());
    }

    private HashMap<String, Object> wss4jProperties() {
        HashMap<String, Object> properties = new HashMap<>();
        StringBuilder acciones = new StringBuilder();
        acciones.append(WSSConstantes.USERNAMETOKEN.getValor()).append(" ")
                .append(WSSConstantes.FIRMADO.getValor()).append(" ")
                .append(WSSConstantes.TIMESTAMP.getValor());
        properties.put(WSHandlerConstants.ACTION, acciones.toString());
        properties.put(WSHandlerConstants.SIGNATURE_USER,
                PropertiesLoader.loadProperty(ConstantsProperties.WSSECURITY_USER.getName()));
        properties.put(WSHandlerConstants.SIG_PROP_REF_ID, "cryptoProperties");
        properties.put(WSHandlerConstants.SIG_KEY_ID, "IssuerSerial");
        properties.put(WSHandlerConstants.INCLUDE_SIGNATURE_TOKEN, "true");
        properties.put(WSHandlerConstants.SIGNATURE_PARTS, ConstantesFirmaDigital.SIGN_PARTS);

        // Configuracion UsernameToken
        properties.put(WSHandlerConstants.USER, PropertiesLoader.loadProperty(ConstantsProperties.TOKEN_USERNAME.getName()));
        properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        properties.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallback.class.getName());
        properties.put("cryptoProperties", cryptoProperties());
        return properties;
    }

    public void setupSsl(SSLContext sslContext) {
        try(InputStream identityInputStream = Files.newInputStream(Paths.get(
                PropertiesLoader.loadProperty(ConstantsProperties.SSL_KEYSTORE_FILE.getName())));
            InputStream trustStoreInputStream = Files.newInputStream(Paths.get(
                PropertiesLoader.loadProperty(ConstantsProperties.SSL_KEYSTORE_FILE.getName())))) {

            KeyStore identity = KeyStore.getInstance(KeyStore.getDefaultType());
            identity.load(identityInputStream,
                    PropertiesLoader.loadProperty(ConstantsProperties.SSL_KEYSTORE_PASSWORD.getName()).toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(identity,
                    PropertiesLoader.loadProperty(ConstantsProperties.SSL_KEYSTORE_PASSWORD.getName()).toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


            KeyStore trusstore = KeyStore.getInstance(KeyStore.getDefaultType());
            trusstore.load(trustStoreInputStream,
                    PropertiesLoader.loadProperty(ConstantsProperties.SSL_KEYSTORE_PASSWORD.getName()).toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trusstore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            sslContext.init(keyManagers, trustManagers, null);
            System.setProperty("http.auth.digest.validateServer", "true");

        } catch (Exception e) {
            LOGGER.error(e.getClass().getName());
            LOGGER.error(e.getCause().getMessage());
            LOGGER.error(e.getMessage());
        }
    }

    public Properties cryptoProperties(){
        Properties properties = new Properties();
        properties.put("org.apache.wss4j.crypto.merlin.provider",
                PropertiesLoader.loadProperty(ConstantsProperties.CRYPTO_PROVIDER.getName()));
        properties.put("org.apache.wss4j.crypto.merlin.keystore.file",
                PropertiesLoader.loadProperty(ConstantsProperties.CRYPTO_FILE.getName()));
        properties.put("org.apache.wss4j.crypto.merlin.keystore.type",
                PropertiesLoader.loadProperty(ConstantsProperties.CRYPTO_TYPE.getName()));
        properties.put("org.apache.wss4j.crypto.merlin.keystore.password",
                PropertiesLoader.loadProperty(ConstantsProperties.WSSECURITY_PASSWORD.getName()));
        properties.put("org.apache.wss4j.crypto.merlin.keystore.alias",
                PropertiesLoader.loadProperty(ConstantsProperties.WSSECURITY_USER.getName()));
        return properties;
    }

}
