package com.landvibe.dstagram.post;

import com.landvibe.dstagram.post.model.Post;
import com.landvibe.dstagram.user.model.CurrentUser;
import com.landvibe.dstagram.user.model.DstagramUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("@security.hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts(@CurrentUser DstagramUser user) {
        return this.postService.getPosts();
    }

    @PreAuthorize("@security.hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@CurrentUser DstagramUser user,
                           @RequestBody Post post) {
        return this.postService.createPost(post);
    }

    @PreAuthorize("@security.hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@CurrentUser DstagramUser user,
                           @PathVariable int id,
                           @RequestBody Post post) {
        // TODO validate id == post.getId()
        return this.postService.updatePost(id, post);
    }

    @PreAuthorize("@security.hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@CurrentUser DstagramUser user,
                           @PathVariable int id) {
        this.postService.deletePost(id);
    }
}
