package automation.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.NOPLogger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;

public class Log {

    private static Logger logger = Logger.getLogger(Log.class.getName());

    public static Logger getLogData(String className){
        String path = new File("").getAbsolutePath();
        DOMConfigurator.configure("log4j.xml");
        return Logger.getLogger(className);
    }

    public static void startTest(String testName){
        logger.info("Test called : " + testName + " has started");
    }
    public static void endTest(String testName){
        logger.info("Test called : " + testName + "has ended");
    }
    public static void info(String message){
        logger.info(message);
    }

    public static void warnings(String message){
        logger.warn(message);
    }

    public static void errors(String message){
        logger.error(message);
    }

    public static void fatal(String message){
        logger.fatal(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }
}
