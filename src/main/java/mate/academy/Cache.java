package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K, V> cache = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(K key, V value) {
        Lock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            cache.remove(key);
        } finally {
            readLock.unlock();
        }
    }

    public void clear() {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            cache.clear();
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return cache.size();
        } finally {
            readLock.unlock();
        }
    }
}
