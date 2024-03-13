package sokolovskaya.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sokolovskaya.dto.User;

import static sokolovskaya.config.CacheConfig.USERS_CACHE;

@Service
@RequiredArgsConstructor
public class UsersService {
    private static final String USERS_API = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplate restTemplate;
    private final Cache usersCache;

    public User[] getUsers() {
        return restTemplate.getForObject(USERS_API, User[].class);
    }

    @Cacheable(USERS_CACHE)
    public User getUser(Long userId) {
        return restTemplate.getForObject(USERS_API + "/" + userId, User.class);
    }

    public User postUser(User user) {
        var responseUser = restTemplate.postForObject(USERS_API, user, User.class);
        usersCache.put(responseUser.getId(), responseUser);
        return responseUser;
    }

    @CachePut(value = USERS_CACHE, key = "#userId")
    public User putUser(User user, Long userId) {
        restTemplate.put(USERS_API + "/" + userId, user);
        return user;
    }

    @CacheEvict(USERS_CACHE)
    public void deleteUser(Long userId) {
        restTemplate.delete(USERS_API + "/" + userId);
    }
}
