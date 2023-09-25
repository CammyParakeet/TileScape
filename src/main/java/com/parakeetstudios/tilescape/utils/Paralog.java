package com.parakeetstudios.tilescape.utils;

import java.util.logging.Logger;

/**
 * @author Cammy
 * @version 1.0
 */

public class Paralog {

    private static Logger logger;

    public static void init(Logger loggerInstance) {
        logger = loggerInstance;
    }


    public static void info(String message) {
        if (logger != null) {
            logger.info(message);
        }
    }

    public static void warning(String message) {
        if (logger != null) {
            logger.warning(message);
        }
    }

    public static void severe(String message) {
        if (logger != null) {
            logger.severe(message);
        }
    }

}
