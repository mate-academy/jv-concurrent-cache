package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock readWriteLock;
    private Map<K, V> cache;

    public Cache() {
        readWriteLock = new ReentrantReadWriteLock();
        cache = new HashMap<>();
    }

    public V get(K key) {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(K key, V value) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            cache.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            cache.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            return cache.size();
        } finally {
            readLock.unlock();
        }
    }
}
