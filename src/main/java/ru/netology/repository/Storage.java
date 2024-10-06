package ru.netology.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private static Storage instance;
    ;
    private static Map<Long, String> posts = new ConcurrentHashMap<>();
    static long counter;

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
        posts.put(counter, str);
        counter++;
        return counter - 1;
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
