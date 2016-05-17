package com.codestub.tsms.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * This class caches the contents
 * Created by ganesh on 30/4/16.
 */
public class Cache {

    private static final Map<String, Cache> instanceHolder = new HashMap<>();
    private final HashMap<String, Object> cacheMap;

    private Cache() {
        this.cacheMap = new HashMap<>();
    }

    public static Cache getInstance(String key) {
        synchronized (instanceHolder) {
            Cache obj = instanceHolder.get(key);
            if (obj == null) {
                obj = new Cache();
                instanceHolder.put(key, obj);
            }
            return obj;
        }
    }

    public Object get(String key) {
        synchronized (cacheMap) {
            return cacheMap.get(key);
        }
    }

    public void put(String key, Object obj) {
        synchronized (cacheMap) {
            if(!cacheMap.containsKey(key)) {
                cacheMap.put(key, obj);
            }
        }
    }
}
