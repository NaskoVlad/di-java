package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Stub
@Repository
public class PostRepository {
    static Storage storage = Storage.getStorage();

    //метод для просмотра всех постов
    public static Map<Long, String> all() {
        return storage.getPosts();
    }

    public static Optional<String> getById(long id) {
        return Optional.ofNullable(storage.getPostsId(id));
    }

    public static Post save(Post post) {
        long id;
        if(post.getId() == 0){
            id = storage.newPosts(post.getContent());
            post.setId(id);
        } else {
            storage.updatePosts(post.getId(), post.getContent());
        }
        return post;
    }

    public static void removeById(long id) {
        storage.delete(id);
    }
}
