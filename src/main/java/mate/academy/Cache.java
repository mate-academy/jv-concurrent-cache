package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReentrantReadWriteLock.ReadLock readLock =
            new ReentrantReadWriteLock().readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock =
            new ReentrantReadWriteLock().writeLock();
    private Map<K, V> map = new HashMap<>();
    private volatile int size;

    public V get(K key) {
        readLock.lock();
        V value;
        try {
            value = map.get(key);
        } finally {
            readLock.unlock();
        }
        return value;
    }

    public void put(K key, V value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(K key) {
        writeLock.lock();
        try {
            map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            map = new HashMap<>();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        return size;
    }
}
