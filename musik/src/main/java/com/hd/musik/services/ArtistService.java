package com.hd.musik.services;

import com.hd.musik.entity.Artist;

import java.util.List;

public interface ArtistService {
    Artist getArtistById(int id);

    List<Artist> getAllArtists();

    List<Artist> getArtistsByWord(String kw);
}
