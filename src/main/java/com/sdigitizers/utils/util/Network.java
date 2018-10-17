
package com.sdigitizers.utils.util;

import com.sdigitizers.utils.fileh.PropertiesFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Shriram Prajapat
 */
public class Network {
    
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    
     /**
     * Get the local-host IP address
     * @return IP Address
     */
    public static String getLocalhostAddress(){
     try { 
         return Inet4Address.getLocalHost().getHostAddress();
       }  catch(UnknownHostException ex) { 
           LOGGER.error("Failed to getLocalhostAddress",ex);  
           return null; } 
    }
    
    
    /**
     * Get MAC address of the local-machine
     * @return MAC Address
     */
    public static String getMAC_Address(){
     try {
         InetAddress address = Inet4Address.getLocalHost();
         NetworkInterface nwi = NetworkInterface.getByInetAddress(address);
         byte mac[] = nwi.getHardwareAddress();
         if(mac!=null){
              StringBuilder sb = new StringBuilder();
                   for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
                    }
              return sb.toString();
         }
         return "N/A";
        }
        catch(UnknownHostException | SocketException ex) { 
            LOGGER.error("Failed to get_MAC_Address",ex); 
        return "N/A"; } 
    }

    
  ////////////////////////////////////////////////////////////////////////////
  
  /**Host - specify the host address (e.g. Website /IP address of the server)
   * Port - Specify the port
   * @param host
   * @param port
   * @return 
   */
  public static boolean isHostPortReachable(String host, int port){
      Socket sock = new Socket();
      InetSocketAddress addr = new InetSocketAddress(host,port);
      try{ sock.connect(addr,3000); return true; }
      catch(IOException ex) {
          LOGGER.error("Error checking host-port reachability ", ex); 
          return false;  } 
       finally {
             try {sock.close();}
             catch (IOException e){}
        }
  }
  /**Host - Specify the local host to be checked for reachability
   * @param host
   * @return 
   */
  public static boolean isHostReachable(String host) {
      try { 
          return Inet4Address.getByName(host).isReachable(5000);
       } catch (IOException ex) {
            LOGGER.error("Error checking host reachability", ex);
          return false;
      }
  }
  
  /**Is Internet available on the machine
   * @return 
   */
  public static boolean isInternetAvailable() {
      try{
          URL url = new URL("http://www.google.com");
          URLConnection con = url.openConnection();
          con.connect();
          return true;
      }catch(IOException ex){
          return false;
      }
  }
  
  
    public static boolean isServerUp(String ip, int port) {
        boolean isUp = false;
            try (Socket socket = new Socket(ip, port)) {
                System.out.println("Server ip "+ip );
                isUp = true;
            } catch (IOException e)  { }
        return isUp;
    }

    
    
    private final String baseUrl;
    private String apiKey;
    private String contentType = "application/json";
    private Properties properties;

    /**
     * @param baseUrl Base URL for the network requests to be made
     */
    public Network(String baseUrl){
        this.baseUrl = baseUrl;
    }
    /**
     * @param baseUrl Base URL for the network requests to be made [you can pass <b>null</b> or <b>"" </b> (empty string) if you have already specified the base url in properties file]<br>
     * @param propertiesFileName name of the properties file where the mapping has been provided for api-urls [base url should 
     * always be specified with the key as <B>base_url</B>]
     */
    public Network(String baseUrl, String propertiesFileName){
        if(!propertiesFileName.endsWith(".properties"))propertiesFileName += ".properties";
        this.properties = PropertiesFile.readPropertiesFileInternal(propertiesFileName);
        this.baseUrl = (null == baseUrl || baseUrl.isEmpty())?properties.getProperty("base_url"):baseUrl;
        if(null == this.baseUrl)LOGGER.error("Base url not specified and also not found in properties file: "+propertiesFileName);
    }
    /**
     * @param prop properties file where the mapping has been provided for api-urls [base url should 
     * always be specified with the key as <B>base_url</B>]
     */
    public Network(Properties prop){
        this.properties = prop;
        this.baseUrl = properties.getProperty("base_url");
        if(null == baseUrl)LOGGER.error("Base URL not found in properties file");
    }
    
    public Network setApiKey(String key){
        this.apiKey = key;
        return this;
    }
    
    /**
     * @param type Post/Put data content type [default = application/json]
     * @return this object
     */
    public Network setContentType(String type){
        this.contentType = type;
        return this;
    }
    
    public String finalUrl(String urlCode, Object... args){
        String finalUrl = null;
        if(null != properties){
            String url = properties.getProperty(urlCode);
            if(null==url){
                LOGGER.error("Requested Mapping ["+urlCode+"] not found in properties file");
                LOGGER.warn("Calling: "+baseUrl+urlCode);
                finalUrl = baseUrl+urlCode;
            }else{
                finalUrl = baseUrl+url;
            }
        }else{
            finalUrl = baseUrl+urlCode;
        }
        if(null != args){
           finalUrl = String.format(finalUrl, args);
        }
        LOGGER.info("Calling: "+finalUrl);
        return finalUrl;
    }
    /**
     * Reads text from specified text file source (Through HTTP)
     *
     * @param urlCode Enter argument as String i.e. the path to file on server
     * @param args
     * @return Text from the file
     */
    public String getData(String urlCode, Object... args) {
        URL url = null;
        StringBuilder content = null;
        String separator = System.getProperty("line.separator");
        try {
            url = new URL(finalUrl(urlCode, args));
            URLConnection conn = url.openConnection();
            HttpURLConnection connection = null;
            if (conn instanceof HttpURLConnection) {
                connection = (HttpURLConnection) conn;
                if(null!=apiKey && !apiKey.isEmpty()){
                    connection.setRequestProperty("authorization", apiKey);
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                content = new StringBuilder();
                String current;
                while ((current = in.readLine()) != null) {
                    content.append(separator).append(current);
                }
                return content.toString();
            } else {
                LOGGER.error("Invalid URL (not http)");
            }
        } catch (IOException ex) {
            LOGGER.error("Specified URL is invalid / not working.. : ", ex);
        }
        return null;
    }

    
    /**
     * Issue delete request with the URL (Through HTTP)
     *
     * @param urlCode Enter argument as String i.e. the path to file on server
     * @param args
     * @return Response
     */
    public String deleteRequest(String urlCode, Object... args) {
        URL url = null;
        StringBuilder content = null;
        String separator = System.getProperty("line.separator");
        try {
            url = new URL(finalUrl(urlCode, args));
            URLConnection conn = url.openConnection();
            HttpURLConnection connection = null;
            if (conn instanceof HttpURLConnection) {
                connection = (HttpURLConnection) conn;
                connection.setRequestMethod("DELETE");
                if(null!=apiKey && !apiKey.isEmpty()){
                    connection.setRequestProperty("authorization", apiKey);
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                content = new StringBuilder();
                String current;
                while ((current = in.readLine()) != null) {
                    content.append(separator).append(current);
                }
                return content.toString();
            } else {
                LOGGER.error("Invalid URL (not http)");
            }
        } catch (IOException ex) {
            LOGGER.error("Specified URL is invalid / not working.. : ", ex);
        }
        return null;
    }
    
    public String postData(String urlCode, String data, Object... args) {
        try {
            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL(finalUrl(urlCode, args)).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", contentType);
            if(null!=apiKey && !apiKey.isEmpty()){
                conn.setRequestProperty("authorization", apiKey);
            }
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final StringBuffer stringBuffer;
            try (final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            LOGGER.error("Error in post : ", e);
            return "Error " + e;
        }
    }
    
    
    
    public String putData(String urlCode, String data, Object... args) {
        try {
            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL(finalUrl(urlCode, args)).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("content-type", contentType);
            if(null!=apiKey && !apiKey.isEmpty()){
                conn.setRequestProperty("authorization", apiKey);
            }
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final StringBuffer stringBuffer;
            try (final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            LOGGER.error("Error in put : ", e);
            return "Error " + e;
        }
    }
    
  
}
