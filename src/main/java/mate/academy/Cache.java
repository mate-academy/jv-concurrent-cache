package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Map<K, V> kvMap = new HashMap<>();

    public V get(K key) {
        V element;
        Lock lock = readWriteLock.readLock();
        lock.lock();
        try {
            element = kvMap.get(key);
        } finally {
            lock.unlock();
        }
        return element;
    }

    public void put(K key, V value) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            kvMap.put(key, value);
        } finally {
            lock.unlock();
        }

    }

    public void remove(K key) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            kvMap.remove(key);
        } finally {
            lock.unlock();
        }

    }

    public void clear() {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            kvMap.clear();
        } finally {
            lock.unlock();
        }

    }

    public int size() {
        Lock lock = readWriteLock.readLock();
        lock.lock();
        try {
            return kvMap.size();
        } finally {
            lock.unlock();
        }
    }
}
