package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K, V> storage = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        return storage.get(key);
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        storage.put(key, value);
        lock.writeLock().unlock();
    }

    public void remove(K key) {
        lock.writeLock().lock();
        storage.remove(key);
        lock.writeLock().unlock();
    }

    public void clear() {
        lock.writeLock().lock();
        storage.clear();
        lock.writeLock().unlock();
    }

    public int size() {
        return storage.size();
    }
}
