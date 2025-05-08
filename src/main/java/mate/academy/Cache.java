package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Map<K, V> map = new HashMap<>();

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            map.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            map.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        readWriteLock.readLock().lock();
        try {
            return map.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
