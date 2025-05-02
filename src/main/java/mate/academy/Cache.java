package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> cashe = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        try {
            lock.readLock().lock();
            return cashe.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        try {
            lock.writeLock().lock();
            cashe.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        try {
            lock.writeLock().lock();
            cashe.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        try {
            lock.writeLock().lock();
            cashe.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        return cashe.size();
    }
}
