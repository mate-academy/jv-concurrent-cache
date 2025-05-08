package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<K, V> storage = new HashMap<>();

    public V get(K key) {
        lock.readLock().lock();
        try {
            return storage.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            storage.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            storage.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            storage.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        return storage.size();
    }
}
