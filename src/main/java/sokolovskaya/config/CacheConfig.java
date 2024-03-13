package sokolovskaya.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String USERS_CACHE = "usersCache";
    public static final String ALBUMS_CACHE = "albumsCache";
    public static final String POSTS_CACHE = "postsCache";

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    public Cache usersCache(CacheManager cacheManager) {
        return cacheManager.getCache(USERS_CACHE);
    }

    @Bean
    public Cache albumsCache(CacheManager cacheManager) {
        return cacheManager.getCache(ALBUMS_CACHE);

    }

    @Bean
    public Cache postsCache(CacheManager cacheManager) {
        return cacheManager.getCache(POSTS_CACHE);
    }
}
