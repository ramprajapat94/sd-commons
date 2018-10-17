package com.sdigitizers.utils.system;

import java.io.IOException;
import static java.lang.System.getProperty;
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
     * @return Process
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
