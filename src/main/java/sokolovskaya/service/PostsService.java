package sokolovskaya.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sokolovskaya.dto.Post;

import static sokolovskaya.config.CacheConfig.POSTS_CACHE;

@Service
@RequiredArgsConstructor
public class PostsService {
    private static final String POSTS_API = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplate restTemplate;
    private final Cache postsCache;

    public Post[] getPosts() {
        return restTemplate.getForObject(POSTS_API, Post[].class);
    }

    @Cacheable(POSTS_CACHE)
    public Post getPost(Long postId) {
        return restTemplate.getForObject(POSTS_API + "/" + postId, Post.class);
    }

    public Post postPost(Post post) {
        var responsePost = restTemplate.postForObject(POSTS_API, post, Post.class);
        postsCache.put(responsePost.getId(), responsePost);
        return responsePost;
    }

    @CachePut(value = POSTS_CACHE, key = "#postId")
    public Post putPost(Post post, Long postId) {
        restTemplate.put(POSTS_API + "/" + postId, post);
        return post;
    }

    @CacheEvict(POSTS_CACHE)
    public void deletePost(Long postId) {
        restTemplate.delete(POSTS_API + "/" + postId);
    }
}
