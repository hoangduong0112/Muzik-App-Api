package com.hd.musik.services.servicesImpl;

import com.hd.musik.entity.Song;
import com.hd.musik.exceptions.ResourceNotFoundException;
import com.hd.musik.repository.SongRepository;
import com.hd.musik.services.SongService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Override
    public Song getSongById(int id) {
        return this.songRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Song ID: %s does not exist", id)));
    }

    @Override
    public List<Song> getSongsByGenre(int genreId, String kw) {
        List<Song> songs = songRepository.findByGenres_Id(genreId);
        if (kw != null && !kw.isEmpty()) {
            songs = songs.stream()
                    .filter(song -> song.getName().toLowerCase().contains(kw.toLowerCase()))
                    .toList();
        }
        return songs;
    }

    @Override
    public List<Song> getAllSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public List<Song> getSongsByKw(String kw) {
        return songRepository.findByNameContainsIgnoreCase(kw);
    }

    @Override
    public List<Song> getSongsByArtist(int artistId, String kw) {
        List<Song> songs = songRepository.findByArtist_Id(artistId);
        if (kw != null && !kw.isEmpty()) {
            songs = songs.stream()
                    .filter(song -> song.getName().toLowerCase().contains(kw.toLowerCase()))
                    .toList();
        }
        return songs;
    }

    @Override
    public List<Song> getSongsByAlbum(int albumId, String kw) {
        List<Song> songs = songRepository.findByAlbum_Id(albumId);
        if (kw != null && !kw.isEmpty()) {
            songs = songs.stream()
                    .filter(song -> song.getName().toLowerCase().contains(kw.toLowerCase()))
                    .toList();
        }
        return songs;
    }

}
