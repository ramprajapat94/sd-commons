
package com.sdigitizers.utils.db;

/**
 *
 * @author Shriram Prajapat
 */
public enum DBServer {
    MY_SQL(3306), ORACLE_SQL(1521), MICROSOFT_SQL(1433), POSTGRE_SQL(5432), H2(9092), DERBY(0);
    
    private final int port;
    private DBServer(int port){
        this.port = port;
    }
    public int getPort(){
        return port;
    }
}
