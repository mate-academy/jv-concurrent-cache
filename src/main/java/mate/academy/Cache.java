package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> innerCache = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(K key) {
        return innerCache.get(key);
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            innerCache.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            innerCache.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            innerCache.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        return innerCache.size();
    }
}
