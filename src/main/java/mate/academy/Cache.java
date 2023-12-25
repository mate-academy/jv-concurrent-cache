package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReentrantReadWriteLock lock;
    private Map<K, V> cache;

    public Cache() {
        this.lock = new ReentrantReadWriteLock();
        this.cache = new HashMap<>();
    }

    public V get(K key) {
        try {
            lock.readLock().lock();
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        try {
            lock.writeLock().lock();
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        try {
            lock.writeLock().lock();
            cache.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        try {
            lock.writeLock().lock();
            cache.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        try {
            lock.readLock().lock();
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
