
package com.sdigitizers.utils.fileh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author Shriram Prajapat
 */
public class JsonUtil {

    /**
     * GSON object to be used by all POJO to serialize/de-serialize to/from JSON
     */
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    public static <T> T fromJson(String json, Class<T> t){
        return GSON.fromJson(json, new TypeToken<T>(){}.getType());
    }

}
