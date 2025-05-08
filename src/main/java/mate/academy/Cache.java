package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> cacheMap = new HashMap<K, V>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        lock.readLock().lock();
        try {
            return cacheMap.get(key);
        } catch (RuntimeException e) {
            throw new RuntimeException("Somethings went wrong during getting cache!", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cacheMap.put(key, value);
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong during saving cache!", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            cacheMap.remove(key);
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong during removing cache!");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            cacheMap.clear();
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong during clearing cache!");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        return cacheMap.size();
    }
}
