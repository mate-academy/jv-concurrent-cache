package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K, V> cache = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            cache.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            cache.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        return cache.size();
    }
}
