package com.codestub.tsms.cache;

import com.codestub.tsms.conversationslist.Conversation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This class caches the contents
 * Created by ganesh on 30/4/16.
 */
public class Cache {

    private static final Map<String, Cache> instanceHolder = new HashMap<>();
    private String key;
    private final HashSet<Conversation> cacheSet;

    private Cache(String key) {
        this.key = key;
        this.cacheSet = new HashSet<>();
    }

    public static Cache getInstance(String key) {
        synchronized (instanceHolder) {
            Cache obj = instanceHolder.get(key);
            if (obj == null) {
                obj = new Cache(key);
                instanceHolder.put(key, obj);
            }
            return obj;
        }
    }

    public Conversation get(String threadID) {
        Conversation conversation = null;
        synchronized (cacheSet) {
            for(Conversation c : cacheSet) {
                if(c.getThreadID().equals(threadID)){
                    conversation = c;
                    break;
                }
            }
            return conversation;
        }
    }

    public void put(Conversation conversation) {
        synchronized (cacheSet) {
            if(!cacheSet.contains(conversation)){
               cacheSet.add(conversation);
            }
        }
    }
}
