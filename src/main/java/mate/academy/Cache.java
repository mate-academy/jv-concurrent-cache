package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Map<K,V> underlyingMap = new HashMap<>();

    public V get(K key) {
        rwLock.readLock().lock();
        try {
            return underlyingMap.get(key);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        rwLock.writeLock().lock();
        try {
            underlyingMap.put(key, value);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        rwLock.writeLock().lock();
        try {
            underlyingMap.remove(key);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void clear() {
        rwLock.writeLock().lock();
        try {
            underlyingMap.clear();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int size() {
        rwLock.readLock().lock();
        try {
            return underlyingMap.entrySet().size();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
