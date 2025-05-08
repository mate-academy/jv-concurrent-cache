package mate.academy;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final HashMap<K, V> hashMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        try {
            lock.readLock().lock();
            return hashMap.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        try {
            lock.writeLock().lock();
            hashMap.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        try {
            lock.writeLock().lock();
            hashMap.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        try {
            lock.writeLock().lock();
            hashMap.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        try {
            lock.readLock().lock();
            return hashMap.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
