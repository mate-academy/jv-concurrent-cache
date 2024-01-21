package mate.academy;

public class Main {
    public static void main(String[] args) {
        Cache<Integer, String> cache = new Cache<>();
        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.clear();
        System.out.println(cache.get(1));
    }
}
