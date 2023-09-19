package com.deceval.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Esta clase se encarga del la lectura del archivo *.properties
 * @author inerjanuerbernate
 */
public class PropertiesLoader {

    static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private static final String PATH_PROPERTIES = "PATH_CLIENT_PROPERTIES";
    private static final String ERROR_LOAD_PROPERTIES = "Error no fue posible cargar el archivo de propiedades";

    private static PropertiesLoader propsLoader = null;

    private Properties properties = null;

    private PropertiesLoader() {
        loadPropertiesFile();
    }

    private static PropertiesLoader getInstance() {
        if (propsLoader == null) {
            propsLoader = new PropertiesLoader();
        }
        return propsLoader;
    }

    public static String loadProperty(String key) {
        return getInstance().properties.getProperty(key);
    }

    private void loadPropertiesFile() {
        String path = System.getenv(PATH_PROPERTIES);
        try (InputStream reader = Files.newInputStream(Paths.get(path))) {
            logger.info(path);
            Properties props = new Properties();
            props.load(reader);
            properties = props;
        } catch (Exception e) {
            logger.error(ERROR_LOAD_PROPERTIES);
            logger.error(e.getClass().getName());
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }

    public static void forcePropertiesFileReload(){
        propsLoader=null;
    }

}

