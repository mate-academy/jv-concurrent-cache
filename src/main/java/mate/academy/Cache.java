package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Map<K, V> cacheMap = new HashMap<>();

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {
            return cacheMap.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            cacheMap.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            cacheMap.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            cacheMap.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        readWriteLock.readLock().lock();
        try {
            return cacheMap.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
