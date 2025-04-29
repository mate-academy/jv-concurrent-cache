package mate.academy;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {
    private final Map<K, V> cache;
    private final Object lock = new Object();

    public Cache() {
        this.cache = new HashMap<>();
    }

    public V get(K key) {
        synchronized (lock) {
            return cache.get(key);
        }
    }

    public void put(K key, V value) {
        synchronized (lock) {
            cache.put(key, value);
        }
    }

    public void remove(K key) {
        synchronized (lock) {
            cache.remove(key);
        }
    }

    public void clear() {
        synchronized (lock) {
            cache.clear();
        }
    }

    public int size() {
        synchronized (lock) {
            return cache.size();
        }
    }
}
