package com.hd.musik.controllers;

import com.hd.musik.entity.Album;
import com.hd.musik.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/album")
@CrossOrigin
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable int id){
        Album album = albumService.getAlbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllAlbums() {
        return new ResponseEntity<>(this.albumService.getAllAlbum(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> searchAlbumsByKw(@RequestParam(name = "kw", required = false) String kw) {
        return new ResponseEntity<>(this.albumService.getAlbumByKw(kw), HttpStatus.OK);
    }
}
