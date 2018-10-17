
package com.sdigitizers.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * (Not ready to use)
 * This class provides functionalities to interact with EXCEL using SQL query<br>
 * Warning: Proper library is required and system configurations are needed
 * @author Shriram Prajapat
 */
public class ExcelSQL {
    
    private static final Logger LOGGER = LogManager.getLogger(ExcelSQL.class);
    
    /**
     * Update/Insert data in Excel file using SQL
     * @param filepath Excel file path (eg.: D://Workbook.xls)
     * @param query (eg.: "insert into [sheetName$] values('value1',553,'value2');")
     * @return true if query was successful, else false
     */
    public static boolean updateExcelSheet(String filepath, String query){
        try {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             Connection a = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};" 
                     + "DBQ="+filepath+"; ReadOnly=False;");
            Statement stmt = a.createStatement();
            return stmt.executeUpdate(query) > 0;
            } catch (ClassNotFoundException | SQLException e) {
                 LOGGER.error("Error updating excel file - ", e);
            } 
        return false;
    }
    
//        java.sql.DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver
//(*.xls)};DBQ=C:/Documents and Settings/myPath/Desktop/qa.xls");"
//+ "
    /**
     * Reads data from Excel file using SQL
     * @param filepath Excel file path (eg.: D://Workbook.xls)
     * @param query (eg.: "SELECT columnName from [sheetName$]")
     */
    public static void readExcelSheet(String filepath, String query){
        try {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             Connection a = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};" 
                     + "DBQ="+filepath+"; ReadOnly=True;");
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                      System.out.println(rs.getString(1));
            }
            } catch (ClassNotFoundException | SQLException e) {
                      LOGGER.error("Error reading excel file - ", e);
            } 
    }
 
}
