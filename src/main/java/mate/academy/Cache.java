package mate.academy;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private HashMap<K, V> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int size = 0;

    public V get(K key) {
        readWriteLock.readLock().lock();
        try {
            return this.map.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            this.map.put(key, value);
            this.size++;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        this.map.remove(key);
    }

    public void clear() {
        this.map = new HashMap<>();
        this.size = 0;
    }

    public int size() {
        return this.size;
    }
}
