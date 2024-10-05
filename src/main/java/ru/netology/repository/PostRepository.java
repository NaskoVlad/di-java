package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
    List<Post> posts = new ArrayList<>();
    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(Math.toIntExact(id)));
    }

    public Post save(Post post) {
            posts.set(Math.toIntExact(post.getId()), post);
        return post;
    }

    public void removeById(long id) {
        posts.remove(Math.toIntExact(id));
    }
}
