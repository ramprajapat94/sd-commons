
package com.sdigitizers.utils.fileh;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Shriram Prajapat
 */
public class PropertiesFile {
    
    private static final Logger LOGGER = LogManager.getLogger(PropertiesFile.class);
    
    /**
     * Read properties file from within the project
     * @param fileName File name to be read
     * @return Properties object with loaded details
     */
    public static Properties readPropertiesFileInternal(String fileName) {
        if(!fileName.endsWith(".properties")) fileName = fileName+".properties";
        Properties prop = null;
        try (final InputStreamReader is = new InputStreamReader(PropertiesFile.class.getClassLoader().getResourceAsStream(fileName))) {
            if (is != null) {
                prop = new Properties();
                prop.load(is);
            }
        } catch (IOException ex) {
            LOGGER.error("Error reading properties file: " + fileName,ex);
        }
        return prop;
    }
    

    /**
     * Read properties file from outside the project(Local disk)
     * @param fileName File name to be read
     * @return Properties object with loaded details
     */
    public static Properties readPropertiesFileExternal(String fileName){
        if(!fileName.endsWith(".properties")) fileName = fileName+".properties";
        try (FileReader reader = new FileReader(fileName)){
            Properties p = new Properties();
            p.load(reader);
            return p;
        } catch (FileNotFoundException ex) {
            LOGGER.error("Specified File Not Found : ",ex);
        } catch (IOException ex) {
            LOGGER.error("I/O Exception : ",ex);
        }
        return null;
    }

    
    
    
       private final String fileName;
       public PropertiesFile(String fileName) {
           this.fileName = fileName;
       }
    
//       public void updatePropValues(HashMap<String, String> values) {
//        try {
//            File propertiesFile = new File(getClass().getClassLoader().getResource(fileName).getFile());
//            PropertiesConfiguration config = new PropertiesConfiguration(propertiesFile);
//            values.keySet().forEach((key) -> {
//                config.setProperty(key, values.get(key));
//            });
//            config.save();
//        } catch (ConfigurationException ex) {
//            LOGGER.error("Error updating properties file",ex);
//        }
//       }

        public Properties getPropValues(){
            InputStream inputStream = null;
            try {
                Properties prop = new Properties();
                inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
                prop.load(inputStream);
                return prop;
            } catch (FileNotFoundException ex) {
                LOGGER.error("Property file '" + fileName + "' not found in the classpath",ex);
            } catch (IOException ex) {
                LOGGER.error("Error reading properties file",ex);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        LOGGER.error("Error reading properties file",ex);
                    }
                }
                }
            return null;
        }

    
}
