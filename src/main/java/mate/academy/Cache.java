package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Map<K, V> kvHashMap = new HashMap<>();

    public V get(K key) {
        V value;
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            value = kvHashMap.get(key);
        } finally {
            readLock.unlock();
        }
        return value;
    }

    public void put(K key, V value) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            kvHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            kvHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            kvHashMap.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        int size;
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            size = kvHashMap.size();
        } finally {
            readLock.unlock();
        }
        return size;
    }
}
