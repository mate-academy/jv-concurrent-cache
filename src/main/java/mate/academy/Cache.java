package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> cacheMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public V get(K key) {
        Lock readlock = lock.readLock();
        readlock.lock();
        try {
           return cacheMap.get(key);
        } finally {
            readlock.unlock();
        }
    }

    public void put(K key, V value) {
        Lock writelock = lock.writeLock();
        writelock.lock();
        try {
            cacheMap.put(key, value);
        } finally {
            writelock.unlock();
        }
    }

    public void remove(K key) {
        Lock writelock = lock.writeLock();
        writelock.lock();
        try {
            cacheMap.remove(key);
        } finally {
            writelock.unlock();
        }
    }

    public void clear() {
        Lock writelock = lock.writeLock();
        writelock.lock();
        try {
            cacheMap.clear();
        } finally {
            writelock.unlock();
        }
    }

    public int size() {
        Lock readlock = lock.readLock();
        readlock.lock();
        try {
            return cacheMap.size();
        } finally {
            readlock.unlock();
        }
    }
}
