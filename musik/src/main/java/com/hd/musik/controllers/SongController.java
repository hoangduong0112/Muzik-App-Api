package com.hd.musik.controllers;


import com.hd.musik.entity.Song;
import com.hd.musik.entity.User;
import com.hd.musik.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/song")
@CrossOrigin
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/")
    public ResponseEntity<?> getAllSongs(@RequestParam(value = "kw", required = false) String kw){
        List<Song> songs = new ArrayList<>();
        if(kw != null)
           songs = songService.getSongsByKw(kw);
        else
            songs = songService.getAllSongs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}/liked")
    public ResponseEntity<?> isSongLike(Authentication authentication, @PathVariable int id){
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>(this.songService.isSongLike(user, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSongById(@PathVariable int id){
        Song song = songService.getSongById(id);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<?> getSongByGenreAndKw(@PathVariable int genreId, @RequestParam(name = "kw", required = false) String kw){
        List<Song> songs = songService.getSongsByGenre(genreId, kw);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<?> getSongByArtistAndKw(@PathVariable int artistId, @RequestParam(name = "kw", required = false) String kw) {
        List<Song> songs = songService.getSongsByAlbum(artistId, kw);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("/album/{albumId}")
    public ResponseEntity<?> getSongByAlbumAndKw(@PathVariable int albumId, @RequestParam(name = "kw", required = false) String kw) {
        List<Song> songs = songService.getSongsByAlbum(albumId, kw);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
