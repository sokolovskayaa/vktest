package sokolovskaya.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sokolovskaya.aud.SaveToAudit;
import sokolovskaya.dto.Album;
import sokolovskaya.service.AlbumsService;

@RestController
@RequestMapping("api/albums")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ALBUMS', 'ADMIN')")
public class AlbumsController {


    private final AlbumsService albumsService;

    @GetMapping
    @SaveToAudit
    public Album[] getAlbums(HttpServletRequest request) {
        return albumsService.getAlbums();
    }

    @GetMapping("/{albumId}")
    @SaveToAudit
    public Album getAlbum(HttpServletRequest request, @PathVariable Long albumId) {
        return albumsService.getAlbum(albumId);
    }

    @PostMapping
    @SaveToAudit
    public Album postAlbum(HttpServletRequest request, @RequestBody Album album) {
        return albumsService.postAlbum(album);
    }

    @PutMapping("/{albumId}")
    @SaveToAudit
    public Album putAlbum(HttpServletRequest request, @RequestBody Album album, @PathVariable Long albumId) {
        return albumsService.putAlbum(album, albumId);
    }

    @DeleteMapping("/{albumId}")
    @SaveToAudit
    public void deleteAlbum(HttpServletRequest request, @PathVariable Long albumId) {
        albumsService.deleteAlbum(albumId);
    }
}
