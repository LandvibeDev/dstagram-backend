package com.landvibe.dstagram.post;

import com.landvibe.dstagram.post.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return this.postRepository.findAll();
    }

    public Post createPost(Post post) {
        if (this.postRepository.findById(post.getId()).isPresent()) {
            // TODO make custom exception
            throw new RuntimeException("This post already exists: " + post.getId());
        } else {
            return this.postRepository.save(post);
        }
    }

    public Post updatePost(int id, Post post) {
        if (this.postRepository.findById(id).isPresent()) {
            return this.postRepository.save(post);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + id);
        }
    }

    public void deletePost(int id) {
        if (this.postRepository.findById(id).isPresent()) {
            this.postRepository.deleteById(id);
        } else {
            // TODO make custom exception
            throw new RuntimeException("Not found post: " + id);
        }
    }
}
