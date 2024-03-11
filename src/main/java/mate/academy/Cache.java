package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<K, V> map;

    public Cache() {
        map = new HashMap<>();
    }

    public Cache(Map<K, V> map) {
        map = map;
    }

    public V get(K key) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(K key, V value) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return map.size();
        } finally {
            readLock.unlock();
        }
    }
}
