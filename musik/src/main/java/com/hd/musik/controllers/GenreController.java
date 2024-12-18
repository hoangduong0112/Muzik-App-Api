package com.hd.musik.controllers;

import com.hd.musik.entity.Genre;
import com.hd.musik.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/genre")
@CrossOrigin
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable int id){
        Genre genre = this.genreService.getGenreById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllGenre() {
        return new ResponseEntity<>(this.genreService.getAllGenres(), HttpStatus.OK);
    }


}
