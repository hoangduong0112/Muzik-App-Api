package com.hd.musik.controllers;


import com.hd.musik.entity.Song;
import com.hd.musik.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/song")
@CrossOrigin
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/")
    public ResponseEntity<?> getAllSongs() {
        return new ResponseEntity<>(songService.getAllSongs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSongById(@PathVariable int id){
        Song song = songService.getSongById(id);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getSongByKw(@RequestParam(name = "kw", required = false) String kw){
        List<Song> songs = songService.getSongsByKw(kw);
        return new ResponseEntity<>(songs, HttpStatus.OK);
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
