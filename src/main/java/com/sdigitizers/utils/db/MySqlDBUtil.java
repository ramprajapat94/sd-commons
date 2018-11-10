
package com.sdigitizers.utils.db;

import com.sdigitizers.utils.system.SystemUtil;
import com.sdigitizers.utils.util.TextUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ensure that MySQL commands are enabled from command prompt
 * @author Shriram Prajapat
 */
public class MySqlDBUtil {
    
 private static final Logger LOGGER = LogManager.getLogger(MySqlDBUtil.class);
    
 /**
  * Creates Backup file (.sql) from Database
  * @param fileName File name with complete path to be restored
  * @param dbParams Database Parameters
  * @return true if successful else false
  */
 public static boolean getDatabaseBackup(DBParams dbParams, String fileName){
     try {
         if(!fileName.endsWith(".sql"))fileName += ".sql";
         File f = new File(fileName);
         if(!f.getParentFile().exists())f.mkdirs();
         String command = " mysqldump -u "+dbParams.getUsername()+" -p"+dbParams.getPassword()+" -h "+dbParams.getHost()+" "+dbParams.getDbName()+" > "+fileName;
         Process p = SystemUtil.runCommand(command);
         int exitCode = p.waitFor();
         switch(exitCode){
             case 0 : return true;   //normal termination, everything is fine
             case 1 :{ //read the error stream
                 String txt = TextUtil.streamToText(p.getErrorStream());
                 LOGGER.error(txt);
                 return !txt.contains("ERROR");
             }
         }
     } catch (InterruptedException ex) {
         LOGGER.error(ex);
     }
     return false;
 } 
 
 /**
  * Restores database from file(.sql)
  * @param dbParams Database Parameters
  * @param createDatabase if database doesn't exist, create one
  * @param fileName File name with complete path to be restored
  * @return true if successful else false
  */
 public static boolean restoreDataBaseBackup(DBParams dbParams, boolean createDatabase, String fileName) {
     try {
         if(!fileName.endsWith(".sql")){
             LOGGER.error("Restoring Database Backup - Not a valid file for sql");
             return false;
         }
         if(createDatabase){
             DBParams newDBParams = dbParams.copyObject();
             newDBParams.setDbName("information_schema");
             executeUpdate(newDBParams, "CREATE DATABASE "+dbParams.getDbName());
         }
         String command = "mysql -u "+dbParams.getUsername()+" -p"+dbParams.getPassword()+" -h "+dbParams.getHost()+" "+dbParams.getDbName()+" < "+fileName;
         Process p = SystemUtil.runCommand(command);
         int exitCode = p.waitFor();
         switch(exitCode){
             case 0 : return true;  
             case 1 :{
                 String txt = TextUtil.streamToText(p.getErrorStream());
                 LOGGER.error(txt);
                 return !txt.contains("ERROR");
             }
         }
     } catch (InterruptedException ex) {
         LOGGER.error(ex);
     }
     return false;
 }

 /**
  * 
  * @param dbParams Database Parameters
  * @param query Query to execute (Only update/insert/delete queries)
  * @return row(s) affected
  */
    public static int executeUpdate(DBParams dbParams, String query) {
     try (DBCon con = new DBCon(dbParams)) {
         return con.executeUpdate(query);
     }
    }
    
    public static boolean changeUserPassword(DBParams params,  String newPass) {
        try{ Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection a = DriverManager.getConnection(params.getConnectionPath(),params.getUsername(),params.getPassword());
                Statement stmt = a.createStatement()) {
              stmt.executeUpdate("UPDATE user SET password = PASSWORD('"+newPass+"') WHERE user='"+params.getUsername()+"';");
              stmt.executeUpdate("FLUSH PRIVILEGES;");
              return true;
        } } catch(ClassNotFoundException | SQLException ex){
            LOGGER.error("Error change user password",ex);
            return false;
        }
    }
    
    /**
     * @param params
     * @param user
     * @param password
     * @param allowedOverHost
     * @return Database parameters with the new user created
     */
//    public static DBParams createNewUser(DBParams params, String user, String password, String allowedOverHost){
//        
//    }
//    
//    public static boolean updateUserAccess(DBParams params, List<String> databases, String allowedOverHost){
//        
//    }
 
 /**
  * Calculates the size of ResultSet (No. of rows contained in the result set)
  * @param rs ResultSet Object
  * @return Size of the given ResultSet object
  */
 public static int getResultSetSize(ResultSet rs){
    if(null == rs)return 0;
    try {
        rs.last();
        int r = rs.getRow();
        rs.beforeFirst();
        return r;
    } catch (SQLException ex) {
       LOGGER.error("Calculating length of result set", ex);
    }
    return 0;
}
 
 /**
  * @since 23-10-2018 By Dhirendra Kumar Sahani
  * @param params Db connection parameters
  * @return MySql version as string
  */
 public static String checkMySQLVersion(DBParams params){
     try(DBCon con = new DBCon(params)){
         try(PreparedStatement ps = con.get().prepareStatement("SHOW VARIABLES LIKE 'VERSION'")){
          try(ResultSet rs = ps.executeQuery()){
              return rs.getString("Value");
          }
         }
     }catch(SQLException ex){
         LOGGER.error(ex);
     }
     return "Not found";
    }
 /**
  * @since 23-10-2018 By Dhirendra Kumar Sahani
  * @param con Active connection reference
  * @return MySql version as string
  */
 public static String checkMySQLVersion(Connection con){
     if(null == con){
         return "Null Connection reference";
     }
     try{
         try(PreparedStatement ps = con.prepareStatement("SHOW VARIABLES LIKE 'VERSION'")){
          try(ResultSet rs = ps.executeQuery()){
              return rs.getString("Value");
          }
         }
     }catch(SQLException ex){
         LOGGER.error(ex);
     }
     return "Not found";
    }
    
}
