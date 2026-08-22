package mate.academy;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final HashMap<K, V> table;

    public Cache() {
        this.table = new HashMap<>();
    }

    public Cache(HashMap<K, V> table) {
        this.table = table;
    }

    public V get(K key) {
        this.lock.readLock().lock();

        try {
            return this.table.get(key);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        this.lock.writeLock().lock();

        try {
            this.table.put(key, value);
        } finally {
            this.lock.writeLock().unlock();
        }

    }

    public void remove(K key) {
        this.lock.writeLock().lock();

        try {
            this.table.remove(key);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void clear() {
        this.lock.writeLock().lock();

        try {
            this.table.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public int size() {
        this.lock.readLock().lock();

        try {
            return this.table.size();
        } finally {
            this.lock.readLock().unlock();
        }

    }
}
