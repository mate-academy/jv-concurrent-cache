package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<K, V> cacheEntryMap = new HashMap<>();

    public V get(K key) {
        lock.readLock().lock();
        try {
            return cacheEntryMap.get(key);
        } catch (Exception e) {
            throw new RuntimeException("Cannot retrieve a value, cause: ", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cacheEntryMap.put(key, value);
        } catch (Exception e) {
            throw new RuntimeException("Cannot put a value, cause: ", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            cacheEntryMap.remove(key);
        } catch (Exception e) {
            throw new RuntimeException("Cannot remove a value, cause: ", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            cacheEntryMap.clear();
        } catch (Exception e) {
            throw new RuntimeException("Cannot clear a cache, cause: ", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return cacheEntryMap.size();
        } catch (Exception e) {
            throw new RuntimeException("Cannot retrieve a cache size, cause: ", e);
        } finally {
            lock.readLock().unlock();
        }
    }
}
