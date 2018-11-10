package com.sdigitizers.utils.system;

import com.sdigitizers.utils.util.Network;
import java.io.IOException;
import static java.lang.System.getProperty;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Shriram Prajapat
 */
public class SystemUtil {

    private static final Logger LOGGER = LogManager.getLogger(SystemUtil.class);

    /**
     * @param command
     * @return Process {call waitFor : returns==0(normal termination),  returns==1(error)}
     */
    public static Process runCommand(String command) {
        Process pp = null;
        try {
            System.out.println("Executing : " + command);
            if (OSValidator.isWindows()) {
                String prg = "C:\\Windows\\system32\\" + "cmd.exe /C ";
                //pp = new ProcessBuilder(command).start();
                pp = Runtime.getRuntime().exec(prg + command);
            } else if (OSValidator.isUnix()) {
                String[] args = new String[]{"/bin/bash", "-c", command};
                pp = new ProcessBuilder(args).start();
            }
        } catch (IOException err) {
            LOGGER.error("Error in execution ", err);
        }
        return pp;
    }
    
    
    public static void printSystemProperties(){
        
        //prpertis related to operating System
        String os = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        
        //System.getProperties().list(System.out);
        
        System.out.println("------OS Properties------ ");
        System.out.println("Operating System Name:"+os);
        System.out.println("User Name:"+user);
        System.out.println(System.getProperty("sun.management.compiler"));
        String osVersion = System.getProperty("os.version");
        System.out.println("OS Version:"+osVersion);
        
        //Properties Related to Java
        System.out.println("------JAVA Properties------ ");
        String javaVersion = System.getProperty("java.version");
        String javaPath = System.getProperty("java.home");
        String javaVendor = System.getProperty("java.vendor");
        
        System.out.println("Java Version:"+javaVersion);
        System.out.println("Java Path:"+javaPath);
        System.out.println("Java Vendor: "+javaVendor);
        
        System.out.println("Internet AVailable  :"+ Network.isInternetAvailable());
        
        //Properties of hardaware
        InetAddress ip;
        try{
            System.out.println("------Hardware Properties------ ");
            ip = InetAddress.getLocalHost();
            LOGGER.error("Current Host Name : "+ip.getHostName());
            System.out.println("Current IP Address : "+ip.getHostAddress());
           
            /*Total number of processors or coresa available to the JVM */
            System.out.println("Available Processors (cores) :" +Runtime.getRuntime().availableProcessors());
            
            /*Total amount of free memory  available to the JVM */
            System.out.println("Free Memory (bytes) :" +Runtime.getRuntime().freeMemory());
            
            /*Total  memory currently in use by  the JVM */
            System.out.println("Total Memory used by JVM (bytes) :" +Runtime.getRuntime().totalMemory());
            
            
        }catch(UnknownHostException ex){
            LOGGER.error(ex);
        }
        
    }

    public static void main(String[] args) {
        
      printSystemProperties();
    }
    
    public static class OSValidator {

        private static final String OS = getProperty("os.name").toLowerCase();

        /**
         * Check if the OS is Windows
         *
         * @return true - if windows else false
         */
        public static boolean isWindows() {
            return (OS.contains("win"));
        }

        /**
         * Check if the OS is MAC
         *
         * @return true - if MAC else false
         */
        public static boolean isMac() {
            return (OS.contains("mac"));
        }

        /**
         * Check if the OS is Linux/ Unix-based OS
         *
         * @return true - if UNIX-based OS else false
         */
        public static boolean isUnix() {
            return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
        }

        /**
         * Check if the OS is Solaris
         *
         * @return true - if Solaris else false
         */
        public static boolean isSolaris() {
            return (OS.contains("sunos"));
        }

        /**
         * Get the OS Code
         *
         * @return win - windows <br> osx - MAC <br> uni - Unix-based <br> sol -
         * Solaris <br> err - Failed to detect (Error)
         */
        public static String getOS() {
            if (isWindows()) {
                return "win";
            } else if (isMac()) {
                return "osx";
            } else if (isUnix()) {
                return "uni";
            } else if (isSolaris()) {
                return "sol";
            } else {
                return "err";
            }
        }

    }
}
