package ru.netology.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Storage {
    private static Storage instance;
    private static Map<Long, String> posts = new ConcurrentHashMap<>();
    private static AtomicLong counter = new AtomicLong();

    private Storage() {
    }

    public static synchronized Storage getStorage() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public static Map<Long, String> getPosts() {
        return posts;
    }

    public static long newPosts(String str) {
        posts.put(counter.get(), str);
        counter.getAndIncrement();
        return counter.get() - 1;
    }

    public static void updatePosts(long lon, String str) {
        if (posts.containsKey(lon)) {
            posts.put(lon, str);
        }
    }

    public static String getPostsId(Long lon) {
        return posts.get(lon);
    }

    public static void delete(Long lon) {
        posts.remove(lon);
    }
}
