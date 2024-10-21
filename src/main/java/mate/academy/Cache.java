package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> cacheMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        try {
            lock.readLock().lock();
            return cacheMap.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        try {
            lock.writeLock().lock();
            cacheMap.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        try {
            lock.writeLock().lock();
            cacheMap.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        try {
            lock.writeLock().lock();
            cacheMap.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        try {
            lock.readLock().lock();
            return cacheMap.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
