package com.sdigitizers.utils.fileh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Shriram Prajapat
 */
public class FileUtil {
    
    private static final Logger LOGGER = LogManager.getLogger(FileUtil.class);
    
    public final static String HOME_DIRECTORY= System.getProperty("user.home")+File.separator+"SanatanDigitizers";
    public final static String REPORTS_DIRECTORY_KCL = HOME_DIRECTORY+File.separator+"KCL";
    public final static String REPORTS_DIRECTORY_VITTSENSE = HOME_DIRECTORY+File.separator+"VittSense";
   

    /**
     * Calculate the file size in standard units
     *
     * @param size of the file (get using length() method)
     * @return File size in KB/MB
     */
    public static String getFileSize(double size) {

        size = size / (1024);
        size = ((double) Math.round(size * 100) / 100);
        if (size < 1025) {
            return (size + " KB");
        } else {
            size = size / 1024;
            size = ((double) Math.round(size * 100) / 100);
            return (size + " MB");
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    public static InputStream toInputStream(File f){
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException ex) {
            LOGGER.error("File I/O error : ", ex);
        }
        return is;
    }
    public static InputStream toInputStream(String filePath){
        return toInputStream(new File(filePath));
    }
    
    ////////////////////
    public static byte[] toBytes(File f){
        try {
	     try (FileInputStream inputStream = new FileInputStream(f)) {
                 byte[] inputBytes = new byte[(int) f.length()];
                 inputStream.read(inputBytes);
                 return inputBytes;
             }
        } catch (IOException ex) {
            LOGGER.error("File I/O error : ", ex);
        }
        return null;
    }

    public static void writeToFile(File outputFile, byte[] b){
        try(FileOutputStream outputStream = new FileOutputStream(outputFile)){
            outputStream.write(b);
            outputStream.flush();
        } catch (IOException ex) {
            LOGGER.error("File I/O error : ", ex);
        }
    }
    
    /**
     * updateFilePerm method converts permission of file by using chmod command
     * which allow either of -rwx- permission to the file.<br>
     * For Unix File System
     *
     * @param fileName
     * @param mode
     * @return
     * @throws java.lang.Exception
     */
    public static boolean updateFilePerm(String fileName, int mode) throws Exception {

        boolean updateError = false;
        int retry = 0; //retry concept
        String changemod = null; //changeMod
        Runtime rt = null; //Runtime class object
        Process proc = null;  //process class object
         if (null == fileName || ("").equals(fileName)) {
            throw new Exception("Source File is null:Null pointer Exception");
        }
        if (mode < 0 || mode > 777) {
            throw new Exception("Invalid Mode");
        }
        try {
            rt = Runtime.getRuntime();
            changemod = "chmod" + " " + mode + " " + fileName;
             do {
                try {
                    proc = rt.exec(changemod);
                    proc.waitFor();
                } catch (IOException ioex) {
                    throw ioex;
                }
                if (proc.exitValue() != 0) {  // in case of abnormal termination
                    updateError = true;
                    retry++;
                } else {
                    updateError = false;
                }
            } while (updateError && retry < 3);
            return updateError;
        } catch (IOException | InterruptedException ex) {
            throw ex;
        }
        
   }
    
    
    
    public static void writeTextFile(File f, String content) {
        try {
            try (PrintWriter out = new PrintWriter(f)) {
                out.println(content);
            }
        } catch (FileNotFoundException ex) {
            LOGGER.error(" : Error writing file - " + ex.getMessage());
        }
    }
    
        
    public static String readTextFile(File f) {
        StringBuilder content = new StringBuilder();;
        //String separator = System.getProperty("line.separator");
        String separator = System.lineSeparator();
        try {
//            try (FileReader reader = new FileReader(f)) {
//                char[] chars = new char[(int) f.length()];
//                reader.read(chars);
//                return new String(chars);
//            }
            try (FileReader reader = new FileReader(f)) {
              BufferedReader bufferedReader = new BufferedReader(reader);
              String line;
              while ((line = bufferedReader.readLine()) != null) {
                  content.append(separator).append(line);
              }
              return content.toString();
            }
        } catch (IOException ex) {
            LOGGER.error("Error reading file - " + ex.getMessage());
        }
        return null;
    }

}
