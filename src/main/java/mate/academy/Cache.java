package mate.academy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ConcurrentHashMap<K, V> hashMap;
    private AtomicInteger size;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock(true);

    public Cache() {
        this.size = new AtomicInteger(0);
        this.hashMap = new ConcurrentHashMap<>();
    }

    public V get(K key) {
        rw.readLock().lock();
        try {
            return hashMap.get(key);
        } finally {
            rw.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        rw.writeLock().lock();
        try {
            hashMap.put(key, value);
            size.incrementAndGet();
        } finally {
            rw.writeLock().unlock();
        }
    }

    public void remove(K key) {
        rw.writeLock().lock();
        try {
            hashMap.remove(key);
            size.decrementAndGet();
        } finally {
            rw.writeLock().unlock();
        }
    }

    public void clear() {
        rw.writeLock().lock();
        try {
            hashMap.clear();
            size.set(0);
        } finally {
            rw.writeLock().unlock();
        }
    }

    public int size() {
        return size.get();
    }
}
