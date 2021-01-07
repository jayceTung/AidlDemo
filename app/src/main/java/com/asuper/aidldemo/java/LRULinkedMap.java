package com.asuper.aidldemo.java;

import java.util.LinkedHashMap;

/**
 * @author SuperM1n
 * @brief description
 * @date 2021-01-06
 */
public class LRULinkedMap<K, V> {
    private int cacheSize;
    private LinkedHashMap<K, V> cacheMap;

    public LRULinkedMap(int cacheSize) {
        this.cacheSize = cacheSize;

        cacheMap = new LinkedHashMap(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Entry eldest) {
                if (cacheSize + 1 == cacheMap.size()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public V get(K key) {
        return cacheMap.get(key);
    }
}
