package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Cache() {
        map = new HashMap<>();
    }

    public V get(K key) {
        if (map.size() > 0) {
            lock.readLock().lock();
            try {
                return map.get(key);
            } finally {
                lock.readLock().unlock();
            }
        } else {
            return null;
        }
    }

    public void put(K key, V value) {
        try {
            lock.writeLock().lock();
            map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            map.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            map.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        return map.size();
    }
}
