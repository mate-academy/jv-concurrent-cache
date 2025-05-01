package mate.academy.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockUtil {
    private static final ReadWriteLock lock = initLock();

    public static ReadWriteLock getLock() {
        return lock;
    }

    private static ReadWriteLock initLock() {
        return new ReentrantReadWriteLock();
    }
}
