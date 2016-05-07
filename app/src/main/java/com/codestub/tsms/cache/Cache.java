package com.codestub.tsms.cache;

import com.codestub.tsms.conversationslist.ConversationTile;

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
    private final HashSet<ConversationTile> cacheSet;

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

    public ConversationTile get(String threadID) {
        ConversationTile conversationTile = null;
        synchronized (cacheSet) {
            for(ConversationTile c : cacheSet) {
                if(c.getThreadID().equals(threadID)){
                    conversationTile = c;
                    break;
                }
            }
            return conversationTile;
        }
    }

    public void put(ConversationTile conversationTile) {
        synchronized (cacheSet) {
            if(!cacheSet.contains(conversationTile)){
               cacheSet.add(conversationTile);
            }
        }
    }
}
