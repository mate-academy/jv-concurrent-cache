package mate.academy;

public class Main {
    public static void main(String[] args) {
        // If you want, you may use this method to test your code during the development
        Cache<Integer, String> cache = new Cache<>();

        // Writing to cache
        cache.put(1, "A");
        cache.put(2, "B");

        // Reading from cache
        System.out.println(cache.get(1)); // Expected output: A
        System.out.println(cache.get(2)); // Expected output: B
        System.out.println(cache.get(3)); // Expected output: null

        // Clearing cache
        cache.clear();

        // Reading from empty cache
        System.out.println(cache.get(1)); // Expected output: null

        Cache<String, String> cacheStr = new Cache<>();

        // Simulate write operations
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cacheStr.put("key" + i, "value" + i);
                System.out.println("Put: key" + i + " -> value" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Simulate read operations
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader 1: key" + i + " -> " + cacheStr.get("key" + i));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader 2: key" + i + " -> " + cacheStr.get("key" + i));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        writer.start();
        reader1.start();
        reader2.start();

        try {
            writer.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Cache Size: " + cacheStr.size());
    }
}
