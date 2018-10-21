
package com.sdigitizers.utils.db;

import com.sdigitizers.utils.crypto.SymmetricCrypt;
import com.sdigitizers.utils.fileh.FileUtil;
import com.sdigitizers.utils.fileh.PropertiesFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Shriram Prajapat
 */
public class DBParams {

    private static final Logger LOGGER = LogManager.getLogger(DBParams.class);
    
    private DBServer databaseServer;
    private String name;
    private String host;
    private int port;
    private String dbName;
    private String username;
    private String password;
    
    private Map<String, String> map = new HashMap<>(6);
    
    public DBParams(String propFileName){
        this(propFileName.contains(":")?PropertiesFile.readPropertiesFileExternal(propFileName):PropertiesFile.readPropertiesFileInternal(propFileName));
    }
    
    public DBParams(Properties propFile){
        this.databaseServer = DBServer.valueOf(propFile.getProperty(map.getOrDefault("databaseServer", "databaseServer"), "MY_SQL"));
        this.host = propFile.getProperty(map.getOrDefault("host", "host"));
        String portFound = propFile.getProperty(map.getOrDefault("port", "port"));
        if(null != portFound){
            this.port = Integer.parseInt(portFound);
        }
        else {
            this.port = databaseServer.getPort();
        }
        this.dbName = propFile.getProperty(map.getOrDefault("dbName", "dbName"));
        this.username = propFile.getProperty(map.getOrDefault("username", "username"));
        this.password = propFile.getProperty(map.getOrDefault("password", "password"));
    }
    
    public DBParams(Properties propFile, String key) {
        if(null != key)SymmetricCrypt.perform().setSecretKey(key);
        overrideParametersWithEncoded();
        try{
            this.databaseServer = DBServer.MY_SQL;
            String serverFound = propFile.getProperty(map.getOrDefault("databaseServer", "databaseServer"));
            if(null != serverFound){ this.databaseServer = DBServer.valueOf(SymmetricCrypt.perform().decrypt(serverFound)); }
            this.host = SymmetricCrypt.perform().decrypt(propFile.getProperty(map.getOrDefault("host", "host")));
            String portFound = propFile.getProperty(map.getOrDefault("port", "port"));
            if(null != portFound) { 
                this.port = Integer.parseInt(SymmetricCrypt.perform().decrypt(portFound));
            }else{
                this.port = databaseServer.getPort();
            }
            this.dbName = SymmetricCrypt.perform().decrypt(propFile.getProperty(map.getOrDefault("dbName", "dbName")));
            this.username = SymmetricCrypt.perform().decrypt(propFile.getProperty(map.getOrDefault("username", "username")));
            this.password = SymmetricCrypt.perform().decrypt(propFile.getProperty(map.getOrDefault("password", "password")));
        }catch(Exception ex){
            LOGGER.error(ex);
        }
    }
    
    public DBParams(String propFileName, String key){
        this(propFileName.contains(":")?PropertiesFile.readPropertiesFileExternal(propFileName):PropertiesFile.readPropertiesFileInternal(propFileName), key);
    }
    
    public DBParams(DBServer databaseServer, String host, String dbName, String username, String password) {
        this.databaseServer = databaseServer;
        this.host = host;
        this.port = databaseServer.getPort();
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Write all parameters to a file (properties file)
     * @param path
     * @return File instance
     */
    public File writeToFile(String path){
        String data = "//Sanatan Digitizers Pvt Ltd, (C) 2018-19\n\n"
                + map.getOrDefault("databaseServer", "databaseServer")+" = "+databaseServer+"\n"
                + map.getOrDefault("host", "host")+" = "+host+"\n"
                + map.getOrDefault("port", "port")+" = "+port+"\n"
                + map.getOrDefault("dbName", "dbName")+" = "+dbName+"\n"
                + map.getOrDefault("username", "username")+" = "+username+"\n"
                + map.getOrDefault("password", "password")+" = "+password+"\n";
        File f = new File(path);
        FileUtil.writeTextFile(f, data);
        return f;
    }

    /**
     * Write all parameters to a file (properties file)
     * @param path File path
     * @param key Encrypt values with the given key
     * @return File instance
     * @throws Exception 
     */
    public File writeToFile(String path, String key) throws Exception{
        SymmetricCrypt.perform().setSecretKey(key);
        overrideParametersWithEncoded();
        String data = "//Sanatan Digitizers Pvt Ltd, (C) 2018-19\n\n"
                + map.getOrDefault("databaseServer", "databaseServer")+" = "+SymmetricCrypt.perform().encrypt(databaseServer.toString())+"\n"
                + map.getOrDefault("host", "host")+" = "+SymmetricCrypt.perform().encrypt(host)+"\n"
                + map.getOrDefault("port", "port")+" = "+SymmetricCrypt.perform().encrypt(port+"")+"\n"
                + map.getOrDefault("dbName", "dbName")+" = "+SymmetricCrypt.perform().encrypt(dbName)+"\n"
                + map.getOrDefault("username", "username")+" = "+SymmetricCrypt.perform().encrypt(username)+"\n"
                + map.getOrDefault("password", "password")+" = "+SymmetricCrypt.perform().encrypt(password)+"\n";
        File f = new File(path);
        FileUtil.writeTextFile(f, data);
        return f;
    }
    
    public void resetParamatersDefaultName(){
        map.put("databaseServer", "databaseServer");
        map.put("host", "host");
        map.put("port", "port");
        map.put("dbName", "dbName");
        map.put("username", "username");
        map.put("password", "password");
    }
    
    /**
     * Override default parameter names<br> e.g.: key denotes the actual parameter name that system understands, value is your name preference<br>
  map.put("databaseServer", "databaseServerNew");
        map.put("host", "hostNew");
        map.put("port", "portNew");
        map.put("dbName", "dbNameNew");
        map.put("username", "userNameNew");
        map.put("password", "passwordNew");
     * @param map 
     */
    public void overrideParameterNames(Map<String, String> map){
        if(null != map)
        this.map = map;
    }
    private void overrideParametersWithEncoded(){
        resetParamatersDefaultName();
        map.replace("host", "imgip_app");
        map.replace("port", "bndrgh");
        map.replace("dbName", "store_app");
        map.replace("username", "using_tax");
        map.replace("password", "power_start");
    }
    
    public DBServer getDatabaseServer() {
        return databaseServer;
    }

    public DBParams setDatabaseServer(DBServer databaseServer) {
        this.databaseServer = databaseServer;
        if(port==0)port = databaseServer.getPort();
        return this;
    }

    public String getName() {
        return name;
    }

    public DBParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getHost() {
        return host;
    }

    public DBParams setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public DBParams setPort(int port) {
        this.port = port;
        return this;
    }

    public String getDbName() {
        return dbName;
    }

    public DBParams setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DBParams setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DBParams setPassword(String password) {
        this.password = password;
        return this;
    }
    
    /**
     * @return Get connection path (for e.g. mysql-> jdbc:mysql//host:port/database)
     */
    public String getConnectionPath(){
        switch(databaseServer){
            case MY_SQL : return "jdbc:mysql://"+host+":"+port+"/"+dbName+"";
            case ORACLE_SQL : return "jdbc:oracle:thin:@"+host+":"+port+":xe";
            case H2 : return "jdbc:h2:mem:"+dbName;
            case DERBY : return "jdbc:derby:"+host;
            default: return "jdbc:mysql://"+host+":"+port+"/"+dbName+"";
        }
    }

    /**
     * @return Driver class name (for e.g. mysql-> com.mysql.cj.jdbc.Driver)
     */
    public String getDriverClassName(){
        switch(databaseServer){
            case MY_SQL : return "com.mysql.cj.jdbc.Driver";
            default: return "com.mysql.cj.jdbc.Driver";
        }
    }
    
    @Override
    public String toString() {
        return "ConnectionName: "+name+",\nConnectionPath: "+getConnectionPath()+",\nUser: "+username;
    }
    
    
    public DBParams copyObject(){
        DBParams db = new DBParams(databaseServer, host, dbName, username, password);
        db.setName(name);
        db.setPort(port);
        return db;
    }
      
}
