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
 * This class provides functionalities to interact with MS-Access database file
 * using SQL
 *
 * @author Shriram Prajapat
 */
public class AccessSQL {

    private static final Logger LOGGER = LogManager.getLogger(AccessSQL.class);
    
    /**
     * @param filepath (eg.: C://Users/Ram/Documents/myfile.accdb)
     * @param query traditional SQL query to update/insert
     * @param filePassword
     * @return
     */
    public static boolean updateFile(String filepath, String filePassword, String query) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url = "jdbc:odbc:Driver={Microsoft Access Driver "
                    + "(*.mdb, *.accdb)};DBQ=" + filepath + ";PWD=" + filePassword;
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            return st.executeUpdate(query) > 0;
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(" : Error in update/insert- ", e);
        }
        return false;
    }

    /**
     *
     * @param filepath (eg.: C://Users/Ram/Documents/myfile.accdb)
     * @param query traditional SQL query to update/insert
     * @param filePassword
     */
    public static void readFile(String filepath, String filePassword, String query) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url = "jdbc:odbc:Driver={Microsoft Access Driver "
                    + "(*.mdb, *.accdb)};DBQ=" + filepath + ";PWD=" + filePassword;
            try (Connection con = DriverManager.getConnection(url); Statement st = con.createStatement()) {
            try (ResultSet rss = st.executeQuery(query)) {
                    while (rss.next()) {

                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Error in reading- ", e);
        }
    }
    
    //String sql = "INSERT INTO bill([Slno],[Item],[Rate],[Qty],[Qty-typ],[Discount],[Total]) VALUES(?,?,?,?,?,?,?)";
    //st.executeUpdate("update [bill] set customer='"+customer+"', billno='"+billno+"' where [Slno] = "+model.getValueAt(r,0)+"");
    //Runtime.getRuntime().exec("wscript D:\\Store_data\\Aprog\\report.vbs");
}
