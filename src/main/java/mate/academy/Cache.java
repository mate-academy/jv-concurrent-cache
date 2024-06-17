package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> cache = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();  
        }
    }

    public void put(K key, V value) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            cache.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            cache.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return cache.size();
        } finally {
            readLock.unlock();
        }
    }
}
