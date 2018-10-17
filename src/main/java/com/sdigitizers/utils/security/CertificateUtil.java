
package com.sdigitizers.utils.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import org.apache.logging.log4j.LogManager;

/**
 * @see http://tutorials.jenkov.com/java-cryptography/certificate.html
 * @author Shriram Prajapat
 */
public class CertificateUtil {
    
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CertificateUtil.class);
    
    
    public static CertificateFactory certificateFactoryX509(){
        try {
            return CertificateFactory.getInstance("X.509");
        } catch (CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    /**
     * @param certificateFilepath (eg. my-x509-certificate.crt)
     * @return 
     */
    public static X509Certificate loadCertificateX509(String certificateFilepath){
        try(InputStream certificateInputStream = new FileInputStream(certificateFilepath)){
            return (X509Certificate) certificateFactoryX509().generateCertificate(certificateInputStream);
        } catch (IOException | CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    public static CertPath loadCertificateChainX509(String certificateFilepath){
        try(InputStream certificateInputStream = new FileInputStream(certificateFilepath)){
            return certificateFactoryX509().generateCertPath(certificateInputStream);
        } catch (IOException | CertificateException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
}
