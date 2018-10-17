
package com.sdigitizers.utils.util;

/**
 *
 * @author Shriram Prajapat
 */
public enum NetworkProtocol {
    HTTP(80), HTTPS(443), FTP(21),
    SFTP(22), SSH(22), SMTP(25), SMTPS(465),
    SSL_TLS(587), POP3(110), POP3S(995), DNS(53), IMAP(143), DHCP_SERVER(67), DHCP_CLIENT(68), SMB(139), NTP(123), IRC(194),
    ;
    
    private final int port;
    private NetworkProtocol(int port){
        this.port = port;
    }
    public int getPort(){
        return port;
    }
}
