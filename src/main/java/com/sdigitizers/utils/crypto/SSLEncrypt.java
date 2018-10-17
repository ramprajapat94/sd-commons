
package com.sdigitizers.utils.crypto;

import com.sdigitizers.utils.system.SystemUtil;
import com.sdigitizers.utils.util.TextUtil;
import java.io.File;
import org.slf4j.LoggerFactory;

/**
 * @see This class requires OpenSSL to be installed on system available in command line/terminal to function
 * @author Shriram Prajapat
 */
public class SSLEncrypt {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SSLEncrypt.class);
    
    
    public static boolean encrypt(File inputFile, File outputFile, String password){
        try {
            String command = "openssl enc -aes-256-cbc -salt -in "+inputFile.getAbsolutePath()+" "
                    + "-out "+outputFile.getAbsolutePath()+" "
                    + "-pass pass:"+password;
            Process pp = SystemUtil.runCommand(command);
            pp.waitFor();
            //Thread.sleep(8000);
            if(null != pp.getErrorStream()){
                LOGGER.error(TextUtil.streamToText(pp.getErrorStream()));
                return false;
            }
        } catch (InterruptedException ex) {
            LOGGER.error("SSL Encrypt Error", ex);
        }
        return false;
    }
    
    public static boolean decrypt(File inputFile, File outputFile, String password){
        try {
            String command = "openssl aes-256-cbc -d -in "+inputFile.getAbsolutePath()+" "
                          + "-out "+outputFile.getAbsolutePath()+" "
                          + "-k  "+password;
            Process pp = SystemUtil.runCommand(command);
            pp.waitFor();
            //Thread.sleep(8000);
            if(null != pp.getErrorStream()){
                LOGGER.error(TextUtil.streamToText(pp.getErrorStream()));
                return false;
            }
        } catch (InterruptedException ex) {
            LOGGER.error("SSL Decrypt Error", ex);
        }
        return false;
    }
    
    
}
