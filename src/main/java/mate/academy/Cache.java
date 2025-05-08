package mate.academy;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private HashMap<K, V> cacheMap = new HashMap<>();

    public V get(K key) {
        lock.readLock().lock();
        try {
            return cacheMap.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cacheMap.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            cacheMap.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            cacheMap.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return cacheMap.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
