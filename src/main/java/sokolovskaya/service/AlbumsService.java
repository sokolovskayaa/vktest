package sokolovskaya.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sokolovskaya.dto.Album;

import static sokolovskaya.config.CacheConfig.ALBUMS_CACHE;

@Service
@RequiredArgsConstructor
public class AlbumsService {
    private static final String ALBUMS_API = "https://jsonplaceholder.typicode.com/albums";

    private final RestTemplate restTemplate;
    private final Cache albumsCache;

    public Album[] getAlbums() {
        return restTemplate.getForObject(ALBUMS_API, Album[].class);
    }

    @Cacheable(ALBUMS_CACHE)
    public Album getAlbum(Long albumId) {
        return restTemplate.getForObject(ALBUMS_API + "/" + albumId, Album.class);
    }

    public Album postAlbum(Album album) {
        var responseAlbum = restTemplate.postForObject(ALBUMS_API, album, Album.class);
        albumsCache.put(responseAlbum.getId(), responseAlbum);
        return responseAlbum;
    }

    @CachePut(value = ALBUMS_CACHE, key = "#albumId")
    public Album putAlbum(Album album, Long albumId) {
        restTemplate.put(ALBUMS_API + "/" + albumId, album);
        return album;
    }

    @CacheEvict(ALBUMS_CACHE)
    public void deleteAlbum(Long albumId) {
        restTemplate.delete(ALBUMS_API + "/" + albumId);
    }
}
