package mate.academy;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final HashMap<K, V> storage = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {
            return storage.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            storage.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        readWriteLock.writeLock().lock();
        try {
            storage.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            storage.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        readWriteLock.readLock().lock();
        try {
            return storage.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
