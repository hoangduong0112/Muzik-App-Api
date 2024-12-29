package com.hd.musik.controllers;


import com.hd.musik.entity.Artist;
import com.hd.musik.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/artist")
@CrossOrigin
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable int id){
        Artist artist = artistService.getArtistById(id);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllArtists(@RequestParam(name = "kw", required = false) String kw) {
        if(kw == null)
            return new ResponseEntity<>(this.artistService.getAllArtists(), HttpStatus.OK);
        else
            return new ResponseEntity<>(this.artistService.getArtistsByWord(kw), HttpStatus.OK);
    }


}
