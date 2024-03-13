package sokolovskaya.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sokolovskaya.aud.SaveToAudit;
import sokolovskaya.dto.Post;
import sokolovskaya.service.PostsService;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('POSTS', 'ADMIN')")
public class PostController {

    private final PostsService postsService;

    @GetMapping
    @SaveToAudit
    public Post[] getPosts(HttpServletRequest request) {
        return postsService.getPosts();
    }

    @GetMapping("/{postId}")
    @SaveToAudit
    public Post getPost(HttpServletRequest request, @PathVariable Long postId) {
        return postsService.getPost(postId);
    }

    @PostMapping
    @SaveToAudit
    public Post postPost(HttpServletRequest request, @RequestBody Post post) {
        return postsService.postPost(post);
    }

    @PutMapping("/{postId}")
    @SaveToAudit
    public Post putPost(HttpServletRequest request, @RequestBody Post post, @PathVariable Long postId) {
        return postsService.putPost(post, postId);
    }

    @DeleteMapping("/{postId}")
    @SaveToAudit
    public void deletePost(HttpServletRequest request, @PathVariable Long postId) {
        postsService.deletePost(postId);
    }
}
