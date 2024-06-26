package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> cacheMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    public V get(K key) {
        lock.readLock();
        try {
            return cacheMap.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock();
        try {
            cacheMap.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock();
        try {
            cacheMap.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock();
        lock.readLock();
        try {
            cacheMap.clear();
        } finally {
            lock.writeLock().unlock();
            lock.readLock().unlock();
        }
    }

    public int size() {
        lock.readLock();
        try {
            return cacheMap.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
