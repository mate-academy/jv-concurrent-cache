package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K,V> map = new HashMap<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        map.put(key, value);
        lock.unlock();
    }

    public void remove(K key) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        map.remove(key);
        lock.unlock();
    }

    public void clear() {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        map = new HashMap<>();
        lock.unlock();
    }

    public int size() {
        Lock lock = readWriteLock.readLock();
        lock.lock();
        int res = map.size();
        lock.unlock();
        return res;
    }
}
