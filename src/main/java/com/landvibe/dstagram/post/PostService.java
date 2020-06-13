package com.landvibe.dstagram.post;

import com.landvibe.dstagram.post.model.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    private Map<Long, Post> cachedPosts = new HashMap<>();

    public List<Post> getPosts() {
        return new ArrayList<>(cachedPosts.values());
    }

    public Post createPost(Post post) {
        LocalDateTime createdTime = LocalDateTime.now();
        post.setCreated(createdTime);
        post.setUpdated(createdTime);

        if (cachedPosts.containsKey(post.getId())) {
            // TODO make custom exception
            throw new RuntimeException("This post already exists: " + post.getId());
        } else {
            cachedPosts.put(post.getId(), post);
            return post;
        }
    }

    public Post updatePost(Long id, Post post) {
        LocalDateTime updatedTime = LocalDateTime.now();
        post.setUpdated(updatedTime);
        post.setId(id);

        if (cachedPosts.containsKey(post.getId())) {
            return cachedPosts.put(id, post);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + id);
        }
    }

    public Post deletePost(Long id) {
        if (cachedPosts.containsKey(id)) {
            return cachedPosts.remove(id);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + id);
        }
    }
}
