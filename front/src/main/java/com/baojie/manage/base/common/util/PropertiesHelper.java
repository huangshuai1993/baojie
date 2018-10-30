package com.baojie.manage.base.common.util;



import java.net.URL;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class PropertiesHelper {

    private static final Logger logger = Logger.getLogger(PropertiesHelper.class);

    private static final String configFile = "config.properties";
    private static Configuration config;

    static {
        logger.info("Initializing system.properties ...");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(configFile);
        if (url == null) {
            logger.info("system.properties not found!");
        } else {
            try {
                config = new PropertiesConfiguration(url);
                logger.info("system.properties has been loaded successfully!");
            } catch (ConfigurationException e) {
                logger.error("Exception load system.properties.");
                e.printStackTrace();
            }
        }
    }

    public static String getString(String property) {
        return config.getString(property);
    }

    public static List<Object> getList(String property) {
        return config.getList(property);
    }

    public static String[] getStringArray(String property) {
        return config.getStringArray(property);
    }

    public static String getString(String property, String defaultValue) {
        return config.getString(property, defaultValue);
    }

    public static int getInt(String property) {
        return config.getInt(property);
    }

    public static int getInt(String property, int defaultValue) {
        return config.getInt(property, defaultValue);
    }

    public static long getLong(String property, int defaultValue) {
        return config.getLong(property, defaultValue);
    }

    public static boolean getBoolean(String property) {
        return config.getBoolean(property);
    }

    public static boolean getBoolean(String property, boolean defaultValue) {
        return config.getBoolean(property, defaultValue);
    }

    public static void reload() {
        logger.info("reload system.properties ...");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(configFile);
        if (url == null) {
            logger.info("system.properties not found!");
        } else {
            try {
                config = new PropertiesConfiguration(url);
                logger.info("system.properties has been reloaded successfully!");
            } catch (ConfigurationException e) {
                logger.error("Exception reload system.properties.");
                e.printStackTrace();
            }
        }
    }

}
