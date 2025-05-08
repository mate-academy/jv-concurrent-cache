package mate.academy;

import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {
    private final ConcurrentHashMap<K, V> cacheMap = new ConcurrentHashMap<>();

    public V get(K key) {
        return cacheMap.get(key);
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public void remove(K key) {
        cacheMap.remove(key);
    }

    public void clear() {
        cacheMap.clear();
    }

    public int size() {
        return cacheMap.size();
    }
}
