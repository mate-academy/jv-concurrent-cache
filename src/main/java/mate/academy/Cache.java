package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K, V> cash = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public V get(K key) {
        Lock readLock = lock.readLock();
        readLock.lock();
        V element;
        try {
            element = cash.get(key);
        } finally {
            readLock.unlock();
        }
        return element;
    }

    public void put(K key, V value) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            cash.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            cash.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            cash.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        Lock readLock = lock.readLock();
        readLock.lock();
        int size;
        try {
            size = cash.size();
        } finally {
            readLock.unlock();
        }
        return size;
    }
}
