package com.hd.musik.controllers;

import com.hd.musik.entity.Playlist;
import com.hd.musik.entity.User;
import com.hd.musik.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/playlist")
@CrossOrigin
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/")
    public ResponseEntity<?> getAllPlaylists(Authentication authentication, @RequestParam String kw) {

        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>(playlistService.getPlaylistsByUser(user, kw), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(Authentication authentication, @PathVariable int id) {

        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>(playlistService.getPlaylistById(id, user), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewPlaylist(Authentication authentication, @RequestBody Playlist playlist){
        User user = (User) authentication.getPrincipal();
        Playlist p = playlistService.createPlaylist(playlist, user);

        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePlaylist(Authentication authentication,
                                            @RequestBody Playlist playlist, @PathVariable int id){
        User user = (User) authentication.getPrincipal();
        Playlist p = playlistService.updatePlaylist(id, playlist, user);

        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylistById(@PathVariable int id, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        playlistService.deletePlaylist(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{playlistId}/song/{songId}")
    public ResponseEntity<?> addSongToPlaylistById(@PathVariable int playlistId, @PathVariable int songId,
                                                   Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Playlist playlist = playlistService.addSongToPlaylist(playlistId, songId, user);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @DeleteMapping("/{playlistId}/song/{songId}")
    public ResponseEntity<?> removeSongFromPlaylistById(@PathVariable int playlistId, @PathVariable int songId,
                                                        Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Playlist playlist = playlistService.removeSongFromPlaylist(playlistId, songId, user);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }
}
