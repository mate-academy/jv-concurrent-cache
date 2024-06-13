package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K,V> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();

    public V get(K key) {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(K key, V value) {
        try {
            writeLock.lock();
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        try {
            writeLock.lock();
            map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        try {
            writeLock.lock();
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            return map.size();
        } finally {
            readLock.unlock();
        }
    }
}
