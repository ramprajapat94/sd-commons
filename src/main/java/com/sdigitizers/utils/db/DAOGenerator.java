
package com.sdigitizers.utils.db;

import com.sdigitizers.utils.util.TextUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shriram Prajapat
 */
public class DAOGenerator {
    
    public static void main(String[] args) {
        
    }
    
    public static void generate(DBParams params, String table){
        try(Connection con = new DBCon(params).get()){
          try(PreparedStatement ps = con.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? ORDER BY ORDINAL_POSITION;")){
              ps.setString(1, table);
              try(ResultSet rs = ps.executeQuery()){
                  List<String> cols = new ArrayList<>();
                  while(rs.next()){
                      cols.add(rs.getString(1));
                  }
                  System.out.println(insertQueryGenerator(table, cols));
              }
          }
            
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
    
    private static String insertQueryGenerator(String tableName, List<String> cols){
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(tableName.toLowerCase()).append(" (").append(TextUtil.listToString(cols)).append(") VALUES(");
        
        StringBuilder sbq = new StringBuilder();
        for(int i=0; i<cols.size(); i++){
            sbq.append(",").append("?");
        }
        
        sbq.deleteCharAt(0);
        
        sb.append(sbq.toString()).append(")");
        
        return sb.toString();
        
    }
}
