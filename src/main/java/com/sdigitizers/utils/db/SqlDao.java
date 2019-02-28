
package com.sdigitizers.utils.db;

import java.util.List;

/**
 *
 * @author Shriram Prajapat
 */
public interface SqlDao<T> {
    
    public boolean add(T t);
    public boolean update(T t);
    public boolean delete(T t);
    
    public T get(int id);
    public List<T> getAll();
}
