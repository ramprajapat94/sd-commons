
package com.sdigitizers.utils.db;

import com.sdigitizers.utils.util.ResultCallback;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Shriram Prajapat
 */
public class DBCon implements Closeable{
 
    private static final Logger LOGGER = LogManager.getLogger(DBCon.class);
    
    private final DBParams dbParams;
    private final Connection connection;
    
    public DBCon(DBParams dbParams) {
        this.dbParams = dbParams;
        this.connection = createConnection();
    }
    
    private Connection createConnection(){
        try{ //Class.forName("java.sql.Driver");
	 Connection con = DriverManager.getConnection(dbParams.getConnectionPath(),dbParams.getUsername(),dbParams.getPassword());
             LOGGER.info("Connection successfully created");
             return con;
        }catch(SQLException ex){
            LOGGER.info("Attempting connection: "+dbParams.toString());
            LOGGER.error("Error creating connection", ex);
	}
        return null;
    }
    
    @Override
    public final void close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
    }
    
    public final Connection get(){
        return connection;
    }
    
    public final DBParams getDBParams(){
        return dbParams;
    }
    
    public int executeUpdate(String query, ResultCallback callback) {
       int cnt=0;
       try{
       try(Statement stmt = connection.createStatement()) {
                 cnt = stmt.executeUpdate(query);
                 if(null != callback)callback.onSuccess();
       }} catch(SQLException ex){
               LOGGER.error("Error executing update",ex);
               if(null != callback)callback.onFailed();
       }
       return cnt;
     }
    
    public int executeUpdate(String query) {
       return executeUpdate(query, null);
    }
    
    
}
