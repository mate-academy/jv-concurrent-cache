package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final ReadWriteLock mutex = new ReentrantReadWriteLock();
    public V get(K key) {
        mutex.readLock().lock();
        try {
            return map.get(key);
        } finally {
            mutex.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        mutex.writeLock().lock();
        try {
            map.put(key, value);
        } finally {
            mutex.writeLock().unlock();
        }
    }

    public void remove(K key) {
        mutex.writeLock().lock();
        try {
            map.remove(key);
        } finally {
            mutex.writeLock().unlock();
        }
    }

    public void clear() {
        mutex.writeLock().lock();
        try {
            map.clear();
        } finally {
            mutex.writeLock().unlock();
        }
    }

    public int size() {
        mutex.readLock().lock();
        try {
            return map.size();
        } finally {
            mutex.readLock().unlock();
        }
    }
}
