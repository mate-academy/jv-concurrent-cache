package mate.academy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {
    private Map<K, V> data = new ConcurrentHashMap<>();

    public V get(K key) {
        return data.get(key);
    }

    public void put(K key, V value) {
        data.put(key, value);
    }

    public void remove(K key) {
        data.remove(key);
    }

    public void clear() {
        data.clear();
    }

    public int size() {
        return data.size();
    }
}
