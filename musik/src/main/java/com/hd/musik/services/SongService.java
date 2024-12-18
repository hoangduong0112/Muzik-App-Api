package com.hd.musik.services;

import com.hd.musik.entity.Song;

import java.util.List;

public interface SongService {
    Song getSongById(int id);

    List<Song> getSongsByGenre(int genreId, String kw);

    List<Song> getAllSongs();

    List<Song> getSongsByKw(String kw);

    List<Song> getSongsByArtist(int artistId, String kw);

    List<Song> getSongsByAlbum(int albumId, String kw);
}
