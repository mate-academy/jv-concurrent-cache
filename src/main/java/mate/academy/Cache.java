package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K,V> cash = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {
            return cash.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            cash.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            cash.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();;
        }
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            cash.clear();
        } finally {
            readWriteLock.writeLock().unlock();;
        }
    }

    public int size() {
        readWriteLock.writeLock().lock();
        try {
            return cash.size();
        } finally {
            readWriteLock.writeLock().unlock();;
        }
    }
}
