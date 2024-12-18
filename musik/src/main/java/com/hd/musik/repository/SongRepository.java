package com.hd.musik.repository;

import com.hd.musik.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    List<Song> findByGenres_Id(Integer id);

    List<Song> findByNameContainsIgnoreCase(String name);

    List<Song> findByArtist_Id(Integer id);

    List<Song> findByAlbum_Id(Integer id);
}