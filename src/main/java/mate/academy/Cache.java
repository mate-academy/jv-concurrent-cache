package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Map<K,V> underlyingMap = new HashMap<>();

    public V get(K key) {
        try {
            if (rwLock.readLock().tryLock(3, TimeUnit.SECONDS)) {
                try {
                    return underlyingMap.get(key);
                } finally {
                    rwLock.readLock().unlock();
                }
            } else {
                throw new RuntimeException();
            }
        } catch (InterruptedException ignored) {
            throw new RuntimeException();
        }
    }

    public void put(K key, V value) {
        try {
            if (rwLock.writeLock().tryLock(3, TimeUnit.SECONDS)) {
                try {
                    underlyingMap.put(key, value);
                } catch (Exception e) {
                    System.out.println("ex" + e);
                } finally {
                    rwLock.writeLock().unlock();
                }
            } else {
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(K key) {
        rwLock.writeLock().lock();
        try {
            underlyingMap.remove(key);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void clear() {
        rwLock.writeLock().lock();
        try {
            underlyingMap.clear();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int size() {
        rwLock.readLock().lock();
        try {
            return underlyingMap.entrySet().size();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
