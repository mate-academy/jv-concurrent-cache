package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(K key) {
        Lock lock = readWriteLock.readLock();
        lock.lock();
        try {
        return map.get(key);
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            map.put(key, value);
        } finally {
            lock.unlock();
        }

    }

    public void remove(K key) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            map.remove(key);
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            map.clear();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return map.size();
    }
}
