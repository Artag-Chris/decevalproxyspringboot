package com.deceval.infrastructure.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Esta clase se encarga del la lectura del archivo *.properties
 * @author inerjanuerbernate
 */
public class PropertiesLoader {

    private static PropertiesLoader propsLoader = null;

    private Properties properties = null;

    private PropertiesLoader() throws Exception {
        loadPropertiesFile();
    }

    private static PropertiesLoader getInstance() throws Exception {

        if (propsLoader == null) {
            propsLoader = new PropertiesLoader();
        }
        return propsLoader;
    }

    public static String loadProperty(String key) {
        String value = null;
        try {
            value = getInstance().properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println("Error cargando archivo de propiedades");
            e.printStackTrace();
        }
        return value;
    }

    private void loadPropertiesFile() throws Exception {
        InputStream instr = null;
        try {
            String path = System.getenv("PATH_CLIENT_PROPERTIES");
            System.out.println(path);
            Properties p = System.getProperties();
            FileReader reader = new FileReader(path);
            if (reader != null) {
                Properties props = new Properties();
                props.load(reader);
                properties = props;
            } else {
                Properties props = new Properties();
                instr = this.getClass().getResourceAsStream(path);
                props.load(instr);
                properties = props;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (instr != null) {
                    instr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Forces the reloading of properties file by setting to null
     * the singleton instance.
     */
    public static void forcePropertiesFileReload() {
        propsLoader = null;
    }

}

