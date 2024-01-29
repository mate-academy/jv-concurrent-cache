package mate.academy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ConcurrentHashMap<K, V> hashMap;
    private AtomicInteger size;
    private ReentrantReadWriteLock rwm = new ReentrantReadWriteLock(true);

    public Cache() {
        this.size = new AtomicInteger(0);
        this.hashMap = new ConcurrentHashMap<>();
    }

    public V get(K key) {
        rwm.readLock().lock();
        try {
            return hashMap.get(key);
        } finally {
            rwm.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        rwm.writeLock().lock();
        try {
            hashMap.put(key, value);
            size.incrementAndGet();
        } finally {
            rwm.writeLock().unlock();
        }
    }

    public void remove(K key) {
        rwm.writeLock().lock();
        try {
            hashMap.remove(key);
            size.decrementAndGet();
        } finally {
            rwm.writeLock().unlock();
        }
    }

    public void clear() {
        rwm.writeLock().lock();
        try {
            hashMap.clear();
            size.set(0);
        } finally {
            rwm.writeLock().unlock();
        }
    }

    public int size() {
        return size.get();
    }
}
