
package com.sdigitizers.utils.db;

/**
 *
 * @author Shriram Prajapat
 */
public class SqlField {
    
    private final String name;
    private final SqlDataType dataType;

    public SqlField(String name, SqlDataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public SqlDataType getDataType() {
        return dataType;
    }

    public String getName() {
        return name;
    }

    
    
}
